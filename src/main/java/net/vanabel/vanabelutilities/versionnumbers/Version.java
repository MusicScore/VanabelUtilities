/*
 * Copyright (c) 2019 Vang "MusicScore" Ngo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.vanabel.vanabelutilities.versionnumbers;

import net.vanabel.vanabelutilities.generics.GenericPair;
import net.vanabel.vanabelutilities.strings.StringUtils;
import net.vanabel.vanabelutilities.validator.ArrayValidator;
import net.vanabel.vanabelutilities.validator.NumberValidator;
import net.vanabel.vanabelutilities.validator.StringValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a version (for example, "v1.2.0").
 * Can interpret a version string in any of the following forms:
 * <br><ul>
 *     <li>1.2.0</li>
 *     <li>1.2.0a3</li>
 *     <li>1.2.0-b5</li>
 *     <li>1.2.0-b5-SNAPSHOT</li>
 *     <li>1.2.0-UNIQUE3.2a3</li>
 *     <li>1.2.0-UNIQUE3.2a3</li>
 *     <li>1.2.0rc5-UNIQUE3.2-b102</li>
 *     <li>1.2.0rc5-UNIQUE3.2b102-SNAPSHOT</li>
 *     <li>etc.</li>
 * </ul>
 * More technically, the version string must obey the regular expression
 * "{@code ^(|v)(?<version>(?>[0-9]+(?:\.[0-9]+)*((?:a|b|rc)[0-9]+)?)(-[A-Z]+\g<version>)*)(?:-(b[0-9]+|SNAPSHOT))?$}"
 * (Perl-compatible dialect). You may experiment with what inputs are valid at this Regex101 link:
 * <a href="https://regex101.com/r/zTlD1D/2">https://regex101.com/r/zTlD1D/2</a>.
 * <br><br>For the sake of being concise, allow the following terms to be defined as:
 * <ul>
 *     <li><b>main version:</b> The number-dot-number pattern that comes before the first dash</li>
 *     <li><b>subversion:</b> Any number-dot-number pattern that comes after a dash</li>
 *     <li><b>subversion identifier:</b> Any sequence of uppercase English characters that precedes a subversion
 *         number-dot-number pattern</li>
 *     <li><b>development stage identifier:</b> Any sequence of characters that denotes a development stage. Must
 *         immediately succeed a number-dot-number pattern and must be succeeded by a build number. Cannot be preceded
 *         by a dash</li>
 *     <li><b>build number:</b> Any number succeeding a development stage identifier (e.g., "{@code v1.2.0rc102}" is
 *         build 102), or succeeding the typical build-number syntax at the end of the version string (e.g.,
 *         "{@code v1.2.0-b102}" is build 102)</li>
 *     <li><b>final build number:</b> The build number succeeding the character sequence "{@code -b}" at the very end
 *         of the version string</li>
 * </ul>
 * <br><br>Depends on {@link GenericPair}, {@link StringUtils}, {@link ArrayValidator}, and {@link StringValidator}.
 */
public class Version {

    ////////////////////////////////////////
    //   Enums
    //////////////////////////////////////

    /**
     * Represents one of the many stages of software development.
     */
    public enum DevelopmentStage {
        /**
         * Denotes a pre-alpha version.
         */
        PRE_ALPHA("a"),
        /**
         * Denotes an alpha version.
         */
        ALPHA("a"),
        /**
         * Denotes a (closed) beta version.
         */
        BETA("b"),
        /**
         * Denotes a release candidate version.
         */
        RELEASE_CANDIDATE("rc"),
        /**
         * Denotes a stable release version.
         */
        STABLE,
        /**
         * Denotes a discontinued software project.
         */
        END_OF_LIFE;

        private String identifier = null;

        DevelopmentStage() {
            // Do nothing
        }

        DevelopmentStage(String identifier) {
            this.identifier = identifier;
        }

        /**
         * Returns true if this DevelopmentStage has a unique identifying String.
         * <br>For alpha builds, this is usually "a"
         * <br>For beta builds, this is usually "b"
         * <br>For release candidate builds, this is usually "rc"
         * @return True for alpha, beta, and release candidate builds; false otherwise
         */
        public boolean hasUniqueIdentifier() {
            return identifier != null;
        }

        /**
         * Returns the unique identifying String for this DevelopmentStage, if any.
         * @return "a" for alpha builds, "b" for beta builds, "rc" for release candidate builds, or {@code null} otherwise
         */
        public String getUniqueIdentifier() {
            return identifier;
        }

        /**
         * Returns a DevelopmentStage enumeration for the String.
         * <br>For "a", this will always return {@link #ALPHA}.
         * <br>For "b", this will always return {@link #BETA}.
         * <br>For "rc", this will always return {@link #RELEASE_CANDIDATE}.
         * @param identifier The String input
         * @return A DevelopmentStage enum, or null if no enum has an identifer that matches the given String
         */
        public static DevelopmentStage getFromIdentifier(String identifier) {
            if (identifier == null) {
                return null;
            }

            switch (identifier) {
                case "a":
                    return DevelopmentStage.ALPHA;
                case "b":
                    return DevelopmentStage.BETA;
                case "rc":
                    return DevelopmentStage.RELEASE_CANDIDATE;
            }
            return null;
        }
    }


    ////////////////////////////////////////
    //   Private static methods
    //////////////////////////////////////

    private static String DEFAULT_NESTED_SUBVERSION_MSG = "A subversion cannot have a subversion of its own!";

    // Converts a String from a series of integers separated by dots to an integer array.
    // For example, "1.2.0" becomes {1, 2, 0}.
    private static int[] asVersionIntArray(String version) {
        List<String> splitString = StringUtils.splitString(version, '.').asList();
        int[] array = new int[splitString.size()];
        try {
            for (int i = 0; i < splitString.size(); i++) {
                array[i] = Integer.parseInt(splitString.get(i));
            }
        }
        catch (NumberFormatException nFE) {
            throw new InvalidVersionException("This string has unexpected alphanumeric characters!");
        }
        return array;
    }


    ////////////////////////////////////////
    //   Public static methods
    //////////////////////////////////////

    /**
     * Returns a Version object constructed from the given String.
     * @param version The String to convert to a Version object
     * @return The Version object
     * @throws InvalidVersionException When the String cannot be converted to a Version
     */
    public static Version getFromString(String version) {
        StringValidator.isNotEmpty(version);

        List<String> splitParts = StringUtils.splitString(version, '-').asList();
        Pattern regex = Pattern.compile("^(?:|v)?([0-9]+(?:\\.[0-9]+)*)((a|b|rc)[0-9]+|-b[0-9]+)?(-SNAPSHOT)?$");
        Matcher matcher = regex.matcher(splitParts.get(0));

        if (!matcher.matches()) {
            throw new InvalidVersionException("Invalid version input!");
        }

        DevelopmentStage devStage = DevelopmentStage.STABLE;
        int build = 1;

        if (matcher.group(3) != null) {
            devStage = DevelopmentStage.getFromIdentifier(matcher.group(3));
            build = Integer.parseInt(StringUtils.after(matcher.group(2), matcher.group(3)));
        }

        Version output = new Version(devStage, build, Version.asVersionIntArray(matcher.group(1)));
        Version lastSubversion = output;

        regex = Pattern.compile("^([A-Z]+)([0-9]+(?:\\.[0-9]+)*)((a|b|rc)[0-9]+)?$");

        for (int i = 1; i < splitParts.size(); i++) {
            String part = splitParts.get(i);
            build = 0;
            devStage = DevelopmentStage.STABLE;

            System.out.println(part);

            if (part.matches("SNAPSHOT")) {
                lastSubversion.isSnapshot = true;
                continue;
            }

            if (part.startsWith("b")) {
                if (i != splitParts.size() - 1 && !splitParts.get(i + 1).matches("SNAPSHOT")) {
                    throw new InvalidVersionException("The final build number should be at the very end of the version number!");
                }
                if (lastSubversion.getBuild() > 0) {
                    throw new InvalidVersionException("There cannot be a distinct final build number and distinct development stage build number for this subversion!");
                }

                build = Integer.parseInt(StringUtils.after(part, "b"));
                lastSubversion.build = build;
                lastSubversion.isSnapshot = splitParts.get(i + 1).matches("SNAPSHOT");
                break;
            }

            matcher = regex.matcher(part);

            if (!matcher.matches()) {
                throw new InvalidVersionException("Malformed subversion!");
            }

            String group4 = matcher.group(4);

            if (group4 != null) {
                devStage = DevelopmentStage.getFromIdentifier(group4);
                build = Integer.parseInt(StringUtils.after(matcher.group(3), group4));
            }

            lastSubversion = new Version(devStage, build, Version.asVersionIntArray(matcher.group(2)));
            output.subverList.add(new GenericPair<>(matcher.group(1), lastSubversion));
        }

        return output;
    }


    ////////////////////////////////////////
    //   Instance fields
    //////////////////////////////////////

    private int[] versionNumbers;
    private int build;
    private boolean isSnapshot;
    private DevelopmentStage devStage;

    private boolean isSubVer = false;

    private List<GenericPair<String, Version>> subverList = new ArrayList<>();


    ////////////////////////////////////////
    //   Constructors
    //////////////////////////////////////

    /**
     * Constructs a Version based on a comma-separated list of non-negative integers.
     * The format of the comma-separated list of non-negative integers should be {@code MAJOR VERSION, MINOR VERSION,
     * maintenance, build, ...}, or whichever sequence of numbers denote the version number you intend on using.
     * @param versionNumbers The comma-separated list of non-negative integers denoting the version number
     */
    public Version(int... versionNumbers) {
        this(DevelopmentStage.STABLE, 0, false, versionNumbers);
    }

    /**
     * Constructs a Version based on a specified {@link DevelopmentStage} and a comma-separated list of non-negative
     * integers.
     * The format of the comma-separated list of non-negative integers should be {@code MAJOR VERSION, MINOR VERSION,
     * maintenance, build, ...}, or whichever sequence of numbers denote the version number you intend on using.
     * @param devStage The {@link DevelopmentStage} enum that most accurately describes the version's described
     *                 development stage
     * @param versionNumbers The comma-separated list of non-negative integers denoting the version number
     */
    public Version(DevelopmentStage devStage, int... versionNumbers) {
        this(devStage, 0, false, versionNumbers);
    }

    /**
     * Constructs a Version based on a specified {@link DevelopmentStage}, build number, and comma-separated list of
     * non-negative integers.
     * The format of the comma-separated list of non-negative integers should be {@code MAJOR VERSION, MINOR VERSION,
     * maintenance, build, ...}, or whichever sequence of numbers denote the version number you intend on using.
     * @param devStage The {@link DevelopmentStage} enum that most accurately describes the version's described
     *                 development stage
     * @param build The build number of the version
     * @param versionNumbers The comma-separated list of non-negative integers denoting the version number
     */
    public Version(DevelopmentStage devStage, int build, int... versionNumbers) {
        this(devStage, build, false, versionNumbers);
    }

    /**
     * Constructs a Version based on a specified {@link DevelopmentStage}, a build number, whether the build is a
     * snapshot, and a comma-separated list of non-negative integers.
     * The format of the comma-separated list of non-negative integers should be {@code MAJOR VERSION, MINOR VERSION,
     * maintenance, build, ...}, or whichever sequence of numbers denote the version number you intend on using.
     * @param devStage The {@link DevelopmentStage} enum that most accurately describes the version's described
     *                 development stage
     * @param build The build number of the version
     * @param isSnapshot Whether this version denotes a snapshot build
     * @param versionNumbers The comma-separated list of non-negative integers denoting the version number
     */
    public Version(DevelopmentStage devStage, int build, boolean isSnapshot, int... versionNumbers) {
        ArrayValidator.notEmptyOrNull(versionNumbers);
        for (int number : versionNumbers) {
            NumberValidator.nonNegative(number);
        }

        this.versionNumbers = versionNumbers;
        this.devStage = devStage;
        this.build = NumberValidator.nonNegative(build);
        this.isSnapshot = isSnapshot;
    }


    ////////////////////////////////////////
    //   Private instance methods
    //////////////////////////////////////

    private void clearInvalidPairs() {
        List<GenericPair<String, Version>> untouched = new ArrayList<>(subverList);
        for (GenericPair<String, Version> pair : untouched) {
            if (pair == null || pair.getLeft() == null || pair.getRight() == null) {
                subverList.remove(pair);
            }
        }
    }

    private GenericPair<String, Version> findEntryById(String identifier) {
        clearInvalidPairs();
        for (GenericPair<String, Version> pair : subverList) {
            if (pair.getLeft().matches(identifier)) {
                return pair;
            }
        }
        return null;
    }

    private String getPlainVersionNumber(String id) {
        StringBuilder versionNum = new StringBuilder();
        versionNum.append(versionNumbers[0]);
        for (int i = 1; i < versionNumbers.length; i++) {
            versionNum.append(".").append(versionNumbers[i]);
        }
        return id + versionNum.toString();
    }

    private String getFullVersionNumber(String prefix, boolean showSnap) {
        StringBuilder output = new StringBuilder(getPlainVersionNumber(prefix));
        if (devStage.hasUniqueIdentifier()) {
            output.append(devStage.getUniqueIdentifier()).append(build);
        }
        else if (build > 1 && subverList.isEmpty()) {
            output.append("-b").append(build);
        }

        Version lastSubversion = this;

        if (!subverList.isEmpty()) {
            for (GenericPair<String, Version> pair : subverList) {
                lastSubversion = pair.getRight();
                output.append("-").append(pair.getRight().getFullVersionNumber(pair.getLeft(), false));
            }
        }

        if (showSnap && lastSubversion.isSnapshot) {
            output.append("-SNAPSHOT");
        }

        return output.toString();
    }

    private int getComparisonLevelAgainst(Version targetVersion, boolean deepCompare) {
        int[] arrTo = this.versionNumbers;
        int[] arrAgainst = targetVersion.versionNumbers;
        for (int i = 0; i < Math.min(arrTo.length, arrAgainst.length); i++) {
            if (arrTo[i] > arrAgainst[i]) {
                return 1;
            }
            if (arrTo[i] < arrAgainst[i]) {
                return -1;
            }
        }

        if (!deepCompare) {
            return Integer.compare(arrTo.length, arrAgainst.length);
        }
        return arrTo.length == arrAgainst.length ?
                (build == targetVersion.build ? Integer.compare(devStage.ordinal(), targetVersion.devStage.ordinal()) : Integer.compare(build, targetVersion.build))
                : Integer.compare(arrTo.length, arrAgainst.length);
    }


    ////////////////////////////////////////
    //   Public property methods
    //////////////////////////////////////

    /**
     * Returns whether this version is for a snapshot build.
     * @return Whether this is a snapshot version
     */
    public boolean isSnapshot() {
        return isSnapshot;
    }

    /**
     * Sets whether this is a snapshot version.
     * @param isSnapshot True if this version should denote a snapshot build, false otherwise.
     */
    public void setSnapshot(boolean isSnapshot) {
        this.isSnapshot = isSnapshot;
    }

    /**
     * Gets the development stage of this version.
     * @return The {@link DevelopmentStage} enum representing this version's development stage
     */
    public DevelopmentStage getDevStage() {
        return devStage;
    }

    /**
     * Sets the development stage of this version.
     * See {@link DevelopmentStage} for the usable development stage enum values.
     */
    public void setDevStage(DevelopmentStage devStage) {
        this.devStage = devStage;
    }

    /**
     * Returns the build number of this version.
     * @return The build number
     */
    public int getBuild() {
        return build;
    }

    /**
     * Sets the build number of this build.
     * @param build The new build number
     */
    public void setBuild(int build) {
        this.build = NumberValidator.nonNegative(build);
    }

    /**
     * Returns the major version number of this Version.
     * This is the first element from the array returned by {@link #getVersionNumberAsArray()}.
     * @return The major version number
     */
    public int getMajorVersionNumber() {
        return versionNumbers[0];
    }

    /**
     * Returns the minor version number of this Version.
     * This is the second element from the array returned by {@link #getVersionNumberAsArray()}.
     * @return The minor version number
     */
    public int getMinorVersionNumber() {
        return versionNumbers.length > 1 ? versionNumbers[1] : 0;
    }

    /**
     * Returns a Version that is recognized as a subversion of this Version.
     * @param identifier The String that acts as the subversion identifier
     * @return The Version that represents the subversion
     */
    public Version getNamedSubversion(String identifier) {
        if (isSubVer) {
            return null;
        }
        GenericPair<String, Version> pair = findEntryById(identifier);
        if (pair == null) {
            return null;
        }
        return pair.getRight();
    }

    /**
     * Appends a new Version as a subversion of this Version.
     * <br><b>NOTE:</b> The version being added as a subversion cannot have subversions of its own!
     * @param identifier The String that acts as the subversion identifier
     * @param newVersion The new Version to be added as a subversion
     * @return True if the subversion was added successfully, false otherwise
     */
    public boolean addNamedSubversion(String identifier, Version newVersion) {
        if (isSubVer) {
            throw new IllegalStateException(DEFAULT_NESTED_SUBVERSION_MSG);
        }
        if (findEntryById(identifier) != null) {
            return false;
        }

        return subverList.add(new GenericPair<>(identifier, newVersion));
    }

    /**
     * Removes a subversion based on the subversion identifier from this Version.
     * @param identifier The String that acts as the subversion identifier
     * @return True if the subversion was deleted, false if the subversion could not be deleted.
     *         <br> "False" may be returned if the subversion could not be found (or if it does not exist)
     */
    public boolean removeNamedSubversion(String identifier) {
        if (findEntryById(identifier) == null) {
            return false;
        }

        return subverList.remove(findEntryById(identifier));
    }

    /**
     * Returns the version numbers in an array.
     * @return The array of integers representing this Version's version numbers
     */
    public int[] getVersionNumberAsArray() {
        return versionNumbers;
    }

    /**
     * Returns the version numbers as a human-readable String.
     * @return A String representing only this Version's version numbers. Excludes all subversions and ignores snapshot
     *         status, build number, and development stage.
     */
    public String getPlainVersionNumber() {
        return getPlainVersionNumber("v");
    }

    /**
     * Returns the full version, including as much important information as possible while including every subversion.
     * <br><b>NOTE:</b> Some information may still be excluded from this output due to relevancy and human readability.
     * @return A String representing this entire Version, including all of its subversions.
     */
    public String getFullVersionNumber() {
        return getFullVersionNumber("v", true);
    }

    @Override
    public String toString() {
        return super.toString() + "[version=\"" + getFullVersionNumber() + "\"]";
    }


    ////////////////////////////////////////
    //   Comparison methods
    //////////////////////////////////////

    /**
     * Returns whether the two Version objects are from the same fork.
     * For this to return true, the following conditions must be met:
     * <br><ul>
     *     <li>Both Versions must have matching version numbers, development stages, and build numbers. This ignores
     *     whether the versions are snapshots are not.</li>
     *     <li>Both Versions must have exactly the same amount of subversions, and each subversion must have exactly
     *     matching subversion identifiers AND be in the same order. If not, then this will return false.</li>
     *     <li>Except for the final subversions, each matching pair of subversion must also have matching version
     *     numbers, development stages, and build numbers.</li>
     *     <li>The final subversions must only match in their subversion identifiers. This method does not check to see
     *     if the final subversions match in version numbers, development stages, or build numbers.</li>
     * </ul>
     * @param targetVersion The Version to compare this Version to
     * @return True if both versions are of the same fork, false otherwise
     */
    public boolean isSameForkAs(Version targetVersion) {
        if (getComparisonLevelAgainst(targetVersion, true) != 0 || subverList.size() != targetVersion.subverList.size()) {
            return false;
        }

        for (int i = 0; i < subverList.size(); i++) {
            GenericPair<String, Version> pairTo = subverList.get(i);
            GenericPair<String, Version> pairAgainst = targetVersion.subverList.get(i);

            if (!pairTo.getLeft().matches(pairAgainst.getLeft()) ||
                    (i < subverList.size() - 1 && pairTo.getRight().getComparisonLevelAgainst(pairAgainst.getRight(), false) != 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether the Version object matches the target Version.
     * Assumes that everything must match, including the order and version information of each subversion (i.e. both
     * Versions must be the same fork, and each fork must also have exactly matching information).
     * @param targetVersion The Version being compared against
     * @return True if both Versions match exactly, false otherwise
     */
    public boolean matches(Version targetVersion) {
        return matches(targetVersion, true, true);
    }

    /**
     * Returns whether the Version object matches the target Version.
     * Assumes that both Versions to be exactly the same fork.
     * <br>If {@code matchBuildAndDevStage} is false, then only the version numbers of the Version objects will be
     * considered.
     * @param targetVersion The Version being compared against
     * @param matchBuildAndDevStage Whether to also match build numbers and development stages
     * @return True if both Versions match with the specified settings, false otherwise
     */
    public boolean matches(Version targetVersion, boolean matchBuildAndDevStage) {
        return matches(targetVersion, matchBuildAndDevStage, true);
    }

    /**
     * Returns whether the Version object matches the target Version.
     * <br>If {@code matchBuildAndDevStage} is false, then only the version numbers of the Version objects will be
     * considered.
     * <br>If {@code fullMatch} is false, then this method will ignore any subversions in either Version objects.
     * @param targetVersion The Version being compared against
     * @param matchBuildAndDevStage Whether to also match build numbers and development stages
     * @param fullMatch Whether to also match the Versions' forks or just match the version numbers of the
     *                  non-subversion Version objects
     * @return True if both Versions match with the specified settings, false otherwise
     */
    public boolean matches(Version targetVersion, boolean matchBuildAndDevStage, boolean fullMatch) {
        int compLvl = getComparisonLevelAgainst(targetVersion, matchBuildAndDevStage);

        if (fullMatch && (!subverList.isEmpty() || !targetVersion.subverList.isEmpty())) {
            if (!isSameForkAs(targetVersion)) {
                return false;
            }

            Version to = subverList.get(subverList.size() - 1).getRight();
            Version against = targetVersion.subverList.get(targetVersion.subverList.size() - 1).getRight();
            return to.matches(against, matchBuildAndDevStage, false);
        }
        return getComparisonLevelAgainst(targetVersion, matchBuildAndDevStage) == 0;
    }

    /**
     * Returns whether the Version object is a version less than the target Version.
     * Assumes both Versions must be the same fork.
     * @param targetVersion The Version being compared against
     * @return True if the Version is less than the target Version, false otherwise
     */
    public boolean isLessThan(Version targetVersion) {
        return isLessThan(targetVersion, true);
    }

    /**
     * Returns whether the Version object is a version less than the target Version.
     * <br>If {@code fullMatch} is false, then this method will ignore any subversions in either Version object.
     * @param targetVersion The Version being compared against
     * @param fullMatch Whether to also match the Versions' forks or just match the version numbers of the
     *                  non-subversion Version objects
     * @return True if the Version is less than the target Version, false otherwise
     */
    public boolean isLessThan(Version targetVersion, boolean fullMatch) {
        int compLvl = getComparisonLevelAgainst(targetVersion, true);

        if (fullMatch && (!subverList.isEmpty() || !targetVersion.subverList.isEmpty())) {
            if (!isSameForkAs(targetVersion)) {
                throw new IllegalStateException("The forks \"" + getFullVersionNumber() + "\" and \"" + targetVersion.getFullVersionNumber() + "\" are not the same!");
            }

            Version to = subverList.get(subverList.size() - 1).getRight();
            Version against = targetVersion.subverList.get(targetVersion.subverList.size() - 1).getRight();
            return to.isLessThan(against, false);
        }

        return compLvl == -1;
    }

    /**
     * Returns whether the Version object is a version less than or equal to the target Version.
     * Assumes both Versions must be the same fork.
     * @param targetVersion The Version being compared against.
     * @return True if the Version is less than or equal to the target Version, false otherwise
     */
    public boolean isAtMost(Version targetVersion) {
        return isAtMost(targetVersion, true);
    }

    /**
     * Returns whether the Version object is a version less than or equal to the target Version.
     * <br>If {@code fullMatch} is false, then this method will ignore any subversions in either Version object.
     * @param targetVersion The Version being compared against
     * @param fullMatch Whether to also match the Versions' forks or just match the version numbers of the
     *                  non-subversion Version objects
     * @return True if the Version is less than or equal to the target Version, false otherwise
     */
    public boolean isAtMost(Version targetVersion, boolean fullMatch) {
        int compLvl = getComparisonLevelAgainst(targetVersion, true);

        if (fullMatch && (!subverList.isEmpty() || !targetVersion.subverList.isEmpty())) {
            if (!isSameForkAs(targetVersion)) {
                throw new IllegalStateException("The forks \"" + getFullVersionNumber() + "\" and \"" + targetVersion.getFullVersionNumber() + "\" are not the same!");
            }

            Version to = subverList.get(subverList.size() - 1).getRight();
            Version against = targetVersion.subverList.get(targetVersion.subverList.size() - 1).getRight();
            return to.isAtMost(against, false);
        }

        return compLvl <= 0;
    }

    /**
     * Returns whether the Version object is a version greater than the target Version.
     * Assumes that both Versions must be the same fork.
     * @param targetVersion The Version being compared against
     * @return True if the Version is greater than the target version, false otherwise
     */
    public boolean isGreaterThan(Version targetVersion) {
        return isGreaterThan(targetVersion, true);
    }

    /**
     * Returns whether the Version object is a version greater than the target Version.
     * <br>If {@code fullMatch} is false, then this method will ignore any subversions in either Version object.
     * @param targetVersion The Version being compared against
     * @param fullMatch Whether to also match the Versions' forks or just match the version numbers of the
     *                  non-subversion Version objects
     * @return True if the Version is greater than the target Version, false otherwise
     */
    public boolean isGreaterThan(Version targetVersion, boolean fullMatch) {
        int compLvl = getComparisonLevelAgainst(targetVersion, true);

        if (fullMatch && (!subverList.isEmpty() || !targetVersion.subverList.isEmpty())) {
            if (!isSameForkAs(targetVersion)) {
                throw new IllegalStateException("The forks \"" + getFullVersionNumber() + "\" and \"" + targetVersion.getFullVersionNumber() + "\" are not the same!");
            }

            Version to = subverList.get(subverList.size() - 1).getRight();
            Version against = targetVersion.subverList.get(targetVersion.subverList.size() - 1).getRight();
            return to.isGreaterThan(against, false);
        }

        return compLvl == 1;
    }

    /**
     * Returns whether the Version object is a version greater than or equal to the target Version.
     * Assumes that both Versions must be the same fork.
     * @param targetVersion The Version being compared against
     * @return True if the Version is greater than or equal to the target version, false otherwise
     */
    public boolean isAtLeast(Version targetVersion) {
        return isAtLeast(targetVersion, true);
    }

    /**
     * Returns whether the Version object is a version greater than or equal to the target Version.
     * <br>If {@code fullMatch} is false, then this method will ignore any subversions in either Version object.
     * @param targetVersion The Version being compared against
     * @param fullMatch Whether to also match the Versions' forks or just match the version numbers of the
     *                  non-subversion Version objects
     * @return True if the Version is greater than or equal to the target Version, false otherwise
     */
    public boolean isAtLeast(Version targetVersion, boolean fullMatch) {
        int compLvl = getComparisonLevelAgainst(targetVersion, true);

        if (fullMatch && (!subverList.isEmpty() || !targetVersion.subverList.isEmpty())) {
            if (!isSameForkAs(targetVersion)) {
                throw new IllegalStateException("The forks \"" + getFullVersionNumber() + "\" and \"" + targetVersion.getFullVersionNumber() + "\" are not the same!");
            }

            Version to = subverList.get(subverList.size() - 1).getRight();
            Version against = targetVersion.subverList.get(targetVersion.subverList.size() - 1).getRight();
            return to.isAtLeast(targetVersion, false);
        }

        return compLvl >= 0;
    }
}
