package net.vanabel.vanabelutilities.strings;

import net.vanabel.vanabelutilities.validator.NumberValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    /**
     * Converts a String to a strict title format. Automatically trims the String.
     * If there are any extra capitalized characters in any given word, those characters will be lowercased.
     * @param str The String to format like a title
     * @return The String in proper title format
     * @see #toTitleCase(String, boolean)
     */
    public static String toTitleCase(String str) {
        return toTitleCase(str, true);
    }

    /**
     * Converts a String to title format. Automatically trims the String.
     * @param str The String to format like a title
     * @param strict Whether to convert every character to lowercase if it is not the first character in any given word
     * @return The String in title format
     * @see #toTitleCase(String)
     */
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

    /**
     * Returns whatever of the first String is after the first instance of the specified second String.
     * If the second String does not exist in the first String, the first String is returned.
     * @param str The String to truncate
     * @param after The String the truncation is based off of
     * @return A substring of the first String, which contains every character after the first instance of the second String
     */
    public static String after(String str, String after) {
        if (!contains(str, after, true)) {
            return str;
        }
        return str.substring(str.indexOf(after) + after.length());
    }

    /**
     * Returns whatever of the first String is after the last instance of the specified second String.
     * If the second String does not exist in the first String, the first String is returned.
     * @param str The String to truncate
     * @param after The String the truncation is based off of
     * @return A substring of the first String, which contains every character after the last instance of the second String
     */
    public static String afterLast(String str, String after) {
        if (!contains(str, after, true)) {
            return str;
        }
        return str.substring(str.lastIndexOf(after) + after.length());
    }

    /**
     * Returns whatever of the first String is before the first instance of the specified second String.
     * If the second String does not exist in the first String, the first String is returned.
     * @param str The String to truncate
     * @param before The String the truncation is based off of
     * @return A substring of the first String, which contains every character before the first instance of the second String
     */
    public static String before(String str, String before) {
        if (!contains(str, before, true)) {
            return str;
        }
        return str.substring(0, str.indexOf(before));
    }

    /**
     * Returns whatever of the first String is before the last instance of the specified second String.
     * If the second String does not exist in the first String, the first String is returned.
     * @param str The String to truncate
     * @param before The String the truncation is based off of
     * @return A substring of the first String, which contains every character before the last instance of the second String
     */
    public static String beforeLast(String str, String before) {
        if (!contains(str, before, true)) {
            return str;
        }
        return str.substring(0, str.lastIndexOf(before));
    }


    ////////////////////////////////////////
    //   String splitting methods
    //////////////////////////////////////

    /**
     * Splits a String based on each space character.
     * @param str The String to split
     * @return A {@link StringPartsData} containing a list of Strings
     * @see #splitString(String, char)
     */
    public static StringPartsData splitString(String str) {
        return splitString(str, ' ');
    }

    /**
     * Splits a String based on each space character, up to a specified amount of parts.
     * @param str The String to split
     * @param maxParts The maximum amount of parts the split String can have
     * @return A {@link StringPartsData} containing a list of Strings
     * @see #splitString(String, char, int)
     */
    public static StringPartsData splitString(String str, int maxParts) {
        return splitString(str, ' ', maxParts);
    }

    /**
     * Splits a String based on a specified character.
     * @param str The String to split
     * @param splitter The character to use when splitting the String
     * @return A {@link StringPartsData} containing a list of Strings
     */
    public static StringPartsData splitString(String str, char splitter) {
        StringPartsData output = new StringPartsData();
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

    /**
     * Splits a String based on a specified character, up to a specified amount of parts.
     * @param str The String to split
     * @param splitter The character to use when splitting the String
     * @param maxParts The maximum amount of parts the split String can have
     * @return A {@link StringPartsData} containing a list of Strings
     */
    public static StringPartsData splitString(String str, char splitter, int maxParts) {
        NumberValidator.isPositive(maxParts);

        StringPartsData output = new StringPartsData();
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

    /**
     * Returns a String as a List of Character objects.
     * @param str The String to convert into a List of Character objects
     * @return A List of Character objects that consists of every character in the original String
     */
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

    /**
     * Returns whether the first String contains the second String.
     * <b>NOTE:</b> If you want to perform a similar operation with case sensitivity, you can also just use {@link String#contains(CharSequence)}.
     * @param str The first String
     * @param searchFor The String that's being searched for in the first String
     * @param caseSensitive Whether the search should be case sensitive
     * @return True if the second String can be found in the first String, false otherwise
     */
    public static boolean contains(String str, String searchFor, boolean caseSensitive) {
        if (!caseSensitive) {
            str = str.toLowerCase();
            searchFor = searchFor.toLowerCase();
        }
        return str.contains(searchFor);
    }

    /**
     * Returns whether the String has any whitespace in it.
     * @param str The String to search in
     * @return True if the String has any whitespace characters in it, false otherwise
     */
    public static boolean hasWhitespace(String str) {
        Pattern pattern = Pattern.compile("\\s");
        return str.isEmpty() || pattern.matcher(str).find();
    }
}
