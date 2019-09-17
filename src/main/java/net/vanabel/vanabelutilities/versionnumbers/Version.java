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
import net.vanabel.vanabelutilities.validator.StringValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a general version string in any of the possible forms:
 * <br><ul>
 *     <li>1.2.0</li>
 *     <li>1.2.0a3</li>
 *     <li>1.2.0-b5</li>
 *     <li>1.2.0-UNIQUE3.2a3</li>
 *     <li>1.2.0-UNIQUE3.2a3</li>
 *     <li>1.2.0rc5-UNIQUE3.2-b102</li>
 *     <li>etc.</li>
 * </ul>
 * More technically, the version string must obey the regular expression
 * "{@code ^(|v)(?<version>(?>[0-9]+(?:\.[0-9]+)*((?:a|b|rc)[0-9]+)?)(-[A-Z]+\g<version>)*)(?:-b[0-9]+)?$}"
 * (Perl-compatible dialect). You may experiment with what inputs are valid at this Regex101 link:
 * <a href="https://regex101.com/r/zTlD1D/1">https://regex101.com/r/zTlD1D/1</a>.
 * <br><br>For the sake of being concise, allow the following terms to be defined as:
 * <ul>
 *     <li><b>main version:</b> The number-dot-number pattern that comes before the first dash</li>
 *     <li><b>subversion:</b> Any number-dot-number pattern that comes after a dash</li>
 *     <li><b>subversion identifier:</b> Any sequence of uppercase English characters that precedes a subversion
 *         number-dot-number pattern</li>
 *     <li><b>development stage identifier:</b> Any sequence of characters that denotes a development stage. Must be
 *         immediately after a number-dot-number pattern and must be succeeded by a build number</li>
 *     <li><b>build number:</b> Any number succeeding a development stage identifier (e.g., "{@code v1.2.0rc102}" is
 *         build 102), or succeeding the typical build-number syntax at the end of the version string (e.g.,
 *         "{@code v1.2.0-b102}" is build 102)</li>
 *     <li><b>final build number:</b> The build number succeeding the character sequence "{@code -b}" at the very end
 *         of the version string</li>
 * </ul>
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
         * Denotes an open beta version.
         */
        OPEN_BETA("b"),
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

        Pattern versionFirstPartRegex = Pattern.compile("^(?:|v)?([0-9]+(?:\\.[0-9]+)*)((a|b|rc)[0-9]+|-b[0-9]+)?(-SNAPSHOT)?$");
        Pattern versionRegex = Pattern.compile("^([A-Z]+)([0-9]+(?:\\.[0-9]+)*)((a|b|rc)[0-9]+)?$");

        Matcher firstPartMatcher = versionFirstPartRegex.matcher(splitParts.get(0));
        if (!firstPartMatcher.matches()) {
            throw new InvalidVersionException("Invalid version input!");
        }

        DevelopmentStage versionDevStage = DevelopmentStage.STABLE;
        int build = 1;
        boolean snapshot = false;
        if (firstPartMatcher.group(3) != null) {
            versionDevStage = DevelopmentStage.getFromIdentifier(firstPartMatcher.group(3));
            build = Integer.parseInt(StringUtils.after(firstPartMatcher.group(2), firstPartMatcher.group(3)));
        }
        if (firstPartMatcher.group(2) != null) {
            if (firstPartMatcher.group(2).startsWith("-b") && splitParts.size() > 1) {
                throw new InvalidVersionException("Cannot declare a final build number on the main version, then have additional unique subversions!");
            }
            build = Integer.parseInt(StringUtils.after(firstPartMatcher.group(2), firstPartMatcher.group(3)));
        }
        if (firstPartMatcher.group(4) != null) {
            snapshot = true;
        }

        Version versionOutput = new Version(Version.asVersionIntArray(firstPartMatcher.group(1)), versionDevStage, build, snapshot);
        Version lastSubVersion = versionOutput;

        for (int i = 1; i < splitParts.size(); i++) {
            String part = splitParts.get(i);
            build = 1;
            versionDevStage = DevelopmentStage.STABLE;
            snapshot = false;

            System.out.println(part);

            Matcher matcher = versionRegex.matcher(part);

            if (part.matches("SNAPSHOT")) {
                lastSubVersion.isSnapshot = true;
                continue;
            }

            if (part.startsWith("b")) {
                if (i != splitParts.size() - 1 && !splitParts.get(i + 1).matches("SNAPSHOT")) {
                    throw new InvalidVersionException("The final build number should be at the very end of the version number!");
                }
                if (lastSubVersion.getBuild() != 1) {
                    throw new InvalidVersionException("There cannot be a final build number and a development stage build number for this subversion!");
                }
                build = Integer.parseInt(StringUtils.after(part, "b"));
                lastSubVersion.build = build;
                lastSubVersion.isSnapshot = splitParts.get(i + 1).matches("SNAPSHOT");
                break;
            }

            if (!matcher.matches()) {
                throw new InvalidVersionException("Malformed subversion!");
            }

            String group4 = matcher.group(4);

            if (group4 != null) {
                versionDevStage = DevelopmentStage.getFromIdentifier(group4);
                build = Integer.parseInt(StringUtils.after(matcher.group(3), group4));
            }

            lastSubVersion = new Version(Version.asVersionIntArray(matcher.group(2)), versionDevStage, build, snapshot);
            versionOutput.addUniqueSubVersion(matcher.group(1), lastSubVersion);
        }

        return versionOutput;
    }


    ////////////////////////////////////////
    //   Instance fields
    //////////////////////////////////////

    private int[] versionParts;
    private int build;
    private boolean isSnapshot;
    private DevelopmentStage devStage;

    private boolean isSubversion = false;
    private String subVersionId = "";

    private List<GenericPair<String, Version>> subVersions = new ArrayList<>();


    ////////////////////////////////////////
    //   Constructors
    //////////////////////////////////////

    public Version(int[] versionParts) {
        this(versionParts, DevelopmentStage.STABLE);
    }

    public Version(int[] versionParts, DevelopmentStage devStage) {
        this(versionParts, devStage, 1);
    }

    public Version(int[] versionParts, DevelopmentStage devStage, int build) {
        this(versionParts, devStage, build, false);
    }

    public Version(int[] versionParts, DevelopmentStage devStage, int build, boolean isSnapshot) {
        this.versionParts = versionParts;
        this.devStage = devStage;
        this.build = build;
        this.isSnapshot = isSnapshot;
    }


    ////////////////////////////////////////
    //   Private instance methods
    //////////////////////////////////////

    private void clearInvalidPairs() {
        List<GenericPair<String, Version>> untouched = new ArrayList<>(subVersions);
        for (GenericPair<String, Version> pair : untouched) {
            if (pair == null || pair.getLeft() == null || pair.getRight() == null) {
                subVersions.remove(pair);
            }
        }
    }

    private GenericPair<String, Version> findEntryById(String identifier) {
        clearInvalidPairs();
        for (GenericPair<String, Version> pair : subVersions) {
            if (pair.getLeft().matches(identifier)) {
                return pair;
            }
        }
        return null;
    }

    private String getPlainVersionNumber(String id) {
        StringBuilder versionNum = new StringBuilder();
        versionNum.append(versionParts[0]);
        for (int i = 1; i < versionParts.length; i++) {
            versionNum.append(".").append(versionParts[i]);
        }
        return id + versionNum.toString();
    }

    private String getFullVersionNumber(String prefix, boolean showSnap) {
        StringBuilder output = new StringBuilder(getPlainVersionNumber(prefix));
        if (devStage.hasUniqueIdentifier()) {
            output.append(devStage.getUniqueIdentifier()).append(build);
        }
        else if (build > 1 && subVersions.isEmpty()) {
            output.append("-b").append(build);
        }

        Version lastSubversion = this;

        if (!subVersions.isEmpty()) {
            for (GenericPair<String, Version> pair : subVersions) {
                lastSubversion = pair.getRight();
                output.append("-").append(pair.getRight().getFullVersionNumber(pair.getLeft(), false));
            }
        }

        if (showSnap && lastSubversion.isSnapshot) {
            output.append("-SNAPSHOT");
        }

        return output.toString();
    }


    ////////////////////////////////////////
    //   Public property methods
    //////////////////////////////////////

    public boolean isSnapshot() {
        return isSnapshot;
    }

    public void setSnapshot(boolean isSnapshot) {
        this.isSnapshot = isSnapshot;
    }

    public DevelopmentStage getDevStage() {
        return devStage;
    }

    public void setDevStage(DevelopmentStage newDevelopmentStage) {
        this.devStage = newDevelopmentStage;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int newBuild) {
        this.build = newBuild;
    }

    public int getMajorVersion() {
        return versionParts[0];
    }

    public int getMinorVersion() {
        return versionParts.length > 1 ? versionParts[1] : 0;
    }

    public String getPlainVersionNumber() {
        return getPlainVersionNumber("v");
    }

    public String getFullVersionNumber() {
        return getFullVersionNumber(isSubversion ? subVersionId : "v", true);
    }

    @Override
    public String toString() {
        return super.toString() + "[version=\"" + getFullVersionNumber() + "\"]";
    }


    ////////////////////////////////////////
    //   Subversion methods
    //////////////////////////////////////

    public boolean addUniqueSubVersion(String identifier, Version version) {
        if (isSubversion) {
            throw new IllegalStateException(DEFAULT_NESTED_SUBVERSION_MSG);
        }
        GenericPair<String, Version> pair = findEntryById(identifier);
        if (pair != null) {
            return false;
        }
        version.isSubversion = true;
        version.subVersionId = identifier;
        subVersions.add(new GenericPair<>(identifier, version));
        return true;
    }

    public void replaceUniqueSubVersion(String identifier, Version newSubVersion) {
        if (isSubversion) {
            throw new IllegalStateException(DEFAULT_NESTED_SUBVERSION_MSG);
        }
        GenericPair<String, Version> pair = findEntryById(identifier);
        if (pair == null) {
            addUniqueSubVersion(identifier, newSubVersion);
        }
        else {
            pair.getRight().isSubversion = false;
            pair.getRight().subVersionId = "";
            newSubVersion.isSubversion = true;
            newSubVersion.subVersionId = identifier;

            pair.setRight(newSubVersion);
        }
    }

    public boolean removeUniqueSubVersion(String identifier) {
        if (isSubversion) {
            throw new IllegalStateException(DEFAULT_NESTED_SUBVERSION_MSG);
        }
        GenericPair<String, Version> pair = findEntryById(identifier);
        if (pair == null) {
            return false;
        }
        pair.getRight().isSubversion = false;
        pair.getRight().subVersionId = "";
        subVersions.remove(pair);
        return true;
    }

    public Version getUniqueSubVersion(String identifier) {
        if (isSubversion) {
            throw new IllegalStateException(DEFAULT_NESTED_SUBVERSION_MSG);
        }
        GenericPair<String, Version> pair = findEntryById(identifier);
        if (pair == null) {
            return null;
        }
        return pair.getRight();
    }
}
