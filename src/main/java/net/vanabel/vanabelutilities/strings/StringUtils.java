package net.vanabel.vanabelutilities.strings;

import net.vanabel.vanabelutilities.validator.NumberValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that holds a collection of methods that are used for manipulating strings in various ways.
 */
public class StringUtils {


    ////////////////////////////////////////
    //   English grammar enums
    //////////////////////////////////////

    /**
     * An enumerated list of all English articles.
     * None of these words should be capitalized in title-cased strings, unless the article begins the title.
     */
    public enum Article {
        /**
         * A helper enumeration for invalid cases, mainly used in {@link #getEnumFor(String)}
         */
        INVALID,
        A,
        AN,
        THE;

        /**
         * Returns the Article that matches (case-insensitive) with the string input.
         * If the string input is not an article, then {@link Article#INVALID} will be returned.
         * @param str The string that is being matched against the enumerated list of English articles
         * @return {@link Article#INVALID} if the string is empty, contains whitespace, or is not an English article; the correct Article otherwise
         */
        public static Article getEnumFor(String str) {
            if (str == null || hasWhitespace(str)) {
                return Article.INVALID;
            }
            for (Article article : Article.values()) {
                if (article.name().matches(str.toUpperCase())) {
                    return article;
                }
            }
            return Article.INVALID;
        }
    }

    /**
     * An enumerated list of all English conjunctions currently known to the API.
     */
    public enum Conjunction {
        /**
         * A helper enumeration for invalid cases, mainly used in {@link #getEnumFor(String)}
         */
        INVALID,
        AFTER,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        AND(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        AS(false),
        BEFORE,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        BUT(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        FOR(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        IF(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        NOR(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        OF(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        OR(false),
        SINCE,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        SO(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        THAN(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        THEN(false),
        TILL,
        UNTIL,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        YET(false);

        boolean isTitleCased;

        Conjunction() {
            this(true);
        }

        Conjunction(boolean isTitleCased) {
            this.isTitleCased = isTitleCased;
        }

        /**
         * Returns whether the conjunction should be capitalized in a title-cased string.
         * @return True if the conjunction should be capitalized, false otherwise.
         */
        public boolean isTitleCased() {
            return isTitleCased;
        }

        /**
         * Returns the Conjunction that matches (case-insensitive) with the string input.
         * If the string input is not a conjugation, then {@link Conjunction#INVALID} will be returned.
         * @param str The string that is being matched against the enumerated list of English conjunctions
         * @return {@link Conjunction#INVALID} if the string is empty, contains whitespace, or is not an English conjunction; the correct Conjunction otherwise
         */
        public static Conjunction getEnumFor(String str) {
            if (str == null || hasWhitespace(str)) {
                return Conjunction.INVALID;
            }
            for (Conjunction article : Conjunction.values()) {
                if (article.name().matches(str.toUpperCase())) {
                    return article;
                }
            }
            return Conjunction.INVALID;
        }
    }

    /**
     * An enumerated list of all English prepositional words currently known to the API.
     * No complex prepositional phrases will be included.
     * The following words will not be included in this list, as they are also widely used as non-prepositional words:
     * "CONCERNING", "CONSIDERING", "COUNTING", "DOWN", "EXCEPTING", "EXCLUDING", "FOLLOWING", "GIVEN", "GONE" (British), "INCLUDING", "INSIDE",
     * "OFF", "OPPOSITE", "OUTSIDE", "PAST", "PRO", "RESPECTING", "ROUND", "SAVE", "SAVING", "TOUCHING", "WORTH"
     */
    public enum Preposition {
        /**
         * A helper enumeration for invalid cases, mainly used in {@link #getEnumFor(String)}
         */
        INVALID,
        ABOARD,
        ABOUT,
        ABOVE,
        ACROSS,
        AFTER,
        AGAINST,
        ALONG,
        ALONGSIDE,
        AMID,
        AMIDST,
        AMONG,
        AMONGST,
        AROUND,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        AS(false),
        ASTRIDE,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        AT(false),
        ATOP,
        BAR,
        BARRING,
        BEFORE,
        BEHIND,
        BELOW,
        BENEATH,
        BESIDE,
        BESIDES,
        BETWEEN,
        BEYOND,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        BUT(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        BY(false),
        CIRCA,
        DESPITE,
        DURING,
        EXCEPT,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        FOR(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        FROM(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        IN(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        INTO(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        LIKE(false),
        NEAR,
        NOTWITHSTANDING,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        OF(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        ON(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        ONTO(false),
        OVER,
        PENDING,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        PER(false),
        REGARDING,
        SINCE,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        THAN(false),
        THROUGH,
        THRU,
        THROUGHOUT,
        TILL,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        TO(false),
        TOWARD,
        TOWARDS,
        UNDER,
        UNDERNEATH,
        UNLIKE,
        UNTIL,
        UP,
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        UPON(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        VERSUS(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        VIA(false),
        /**
         * Shouldn't be capitalized in a title-cased string
         */
        WITH(false),
        WITHIN,
        WITHOUT;

        boolean isTitleCased;

        Preposition() {
            this(true);
        }

        Preposition(boolean isTitleCased) {
            this.isTitleCased = isTitleCased;
        }

        /**
         * Returns whether the preposition should be capitalized in a title-cased string.
         * @return True if the preposition should be capitalized, false otherwise.
         */
        public boolean isTitleCased() {
            return isTitleCased;
        }

        /**
         * Returns the Preposition that matches (case-insensitive) with the string input.
         * If the string input is not a preposition, then {@link Preposition#INVALID} will be returned.
         * @param str The string that is being matched against the enumerated list of English prepositions
         * @return {@link Preposition#INVALID} if the string is empty, contains whitespace, or is not an English preposition; the correct Preposition otherwise
         */
        public static Preposition getEnumFor(String str) {
            if (str == null || hasWhitespace(str)) {
                return Preposition.INVALID;
            }
            for (Preposition article : Preposition.values()) {
                if (article.name().matches(str.toUpperCase())) {
                    return article;
                }
            }
            return Preposition.INVALID;
        }
    }


    ////////////////////////////////////////
    //   Title-case methods
    //////////////////////////////////////

    public static String toTitleCase(String str) {
        return toTitleCase(str, true);
    }

    public static String toTitleCase(String str, boolean strict) {
        str = str.trim();
        if (str.isEmpty()) {
            return "";
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }

        StringBuilder output = new StringBuilder();
        List<String> partsOriginal = splitString(strict ? str.toLowerCase() : str, ' ').asList();
        List<String> partsUppercase = splitString(str.toUpperCase(), ' ').asList();

        if (partsOriginal.size() == 1) {
            return partsUppercase.get(0).charAt(0) + partsOriginal.get(0).substring(1);
        }

        output.append(partsUppercase.get(0).charAt(0)).append(partsOriginal.get(0).substring(1));
        for (int i = 1; i < partsOriginal.size(); i++) {
            output.append(" ");

            String part = partsOriginal.get(i);
            if (part.length() <= 1 || Article.getEnumFor(part) != Article.INVALID
                    || !Conjunction.getEnumFor(str).isTitleCased() || !Preposition.getEnumFor(str).isTitleCased()) {
                output.append(part);
                continue;
            }
            output.append(partsUppercase.get(i).charAt(0)).append(part.substring(1));
        }
        return output.toString();
    }


    ////////////////////////////////////////
    //   Substring methods
    //////////////////////////////////////

    public static String after(String str, String after) {
        if (contains(str, after, true)) {
            return str;
        }
        return str.substring(str.indexOf(after) + after.length());
    }

    public static String afterLast(String str, String after) {
        if (contains(str, after, true)) {
            return str;
        }
        return str.substring(str.lastIndexOf(after) + after.length());
    }

    public static String before(String str, String before) {
        if (contains(str, before, true)) {
            return str;
        }
        return str.substring(0, str.indexOf(before));
    }

    public static String beforeLast(String str, String before) {
        if (contains(str, before, true)) {
            return str;
        }
        return str.substring(0, str.lastIndexOf(before));
    }


    ////////////////////////////////////////
    //   String splitting methods
    //////////////////////////////////////

    public static StringSplitDataHolder splitString(String str) {
        return splitString(str, ' ');
    }

    public static StringSplitDataHolder splitString(String str, int maxParts) {
        return splitString(str, ' ', maxParts);
    }

    public static StringSplitDataHolder splitString(String str, char splitter) {
        StringSplitDataHolder output = new StringSplitDataHolder();
        int s = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == splitter) {
                output.addPart(str.substring(s, i));
                s = i + 1;
            }
        }
        output.addPart(str.substring(s));
        return output;
    }

    public static StringSplitDataHolder splitString(String str, char splitter, int maxParts) {
        NumberValidator.isPositive(maxParts);

        StringSplitDataHolder output = new StringSplitDataHolder();
        int s = 0;
        for (int i = 0; i < str.length(); i++) {
            if (output.size() + 1 >= maxParts) {
                break;
            }
            if (str.charAt(i) == splitter) {
                output.addPart(str.substring(s, i));
                s = i + 1;
            }
        }
        output.addPart(str.substring(s));
        return output;
    }

    public static List<Character> toCharList(String str) {
        List<Character> output = new ArrayList<>();
        for (char character : str.toCharArray()) {
            output.add(character);
        }
        return output;
    }


    ////////////////////////////////////////
    //   Misc. string methods
    //////////////////////////////////////

    public static boolean contains(String str, String searchFor, boolean caseSensitive) {
        if (!caseSensitive) {
            str = str.toLowerCase();
            searchFor = searchFor.toLowerCase();
        }
        return str.contains(searchFor);
    }

    public static boolean hasWhitespace(String str) {
        return str.isEmpty() || str.trim().length() != str.length();
    }
}
