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

package net.vanabel.vanabelutilities.validator;

/**
 * A class that holds a collection of static methods that are used for validating various cases regarding String-like objects.
 */
public class StringValidator {

    ////////////////////////////////////////
    //   Static fields
    //////////////////////////////////////

    private final static String DEFAULT_EMPTY_CHAR_SEQ_MESSAGE = "This character sequence cannot be empty or null!";
    private final static String DEFAULT_NULL_STR_MESSAGE = "This string cannot be null!";


    ////////////////////////////////////////
    //   Empty string methods
    //////////////////////////////////////

    /**
     * Quickly checks if a character sequence object is either empty or null.
     * If the object is either empty or null, an exception is thrown.
     * @param charSequenceObj The character sequence object to check.
     * @param <T> The character sequence type.
     * @return The character sequence if it is not empty nor null.
     * @throws NullPointerException When the sequence is null.
     * @throws IllegalArgumentException When the sequence is empty.
     */
    public static <T extends CharSequence> T isNotEmpty(T charSequenceObj) {
        return isNotEmpty(charSequenceObj, DEFAULT_EMPTY_CHAR_SEQ_MESSAGE);
    }

    /**
     * Quickly checks if a character sequence object is either empty or null.
     * If the object is either empty or null, an exception is thrown.
     * @param charSequenceObj The character sequence object to check.
     * @param message The exception message if the character sequence is empty or null.
     * @param <T> The character sequence type.
     * @return The character sequence if it is not empty nor null.
     * @throws NullPointerException When the sequence is null.
     * @throws IllegalArgumentException When the sequence is empty.
     */
    public static <T extends CharSequence> T isNotEmpty(T charSequenceObj, String message) {
        if (charSequenceObj == null) {
            throw new NullPointerException(message);
        }
        if (charSequenceObj.length() == 0) {
            throw new IllegalArgumentException(message);
        }
        return charSequenceObj;
    }


    ////////////////////////////////////////
    //   Null string methods
    //////////////////////////////////////

    /**
     * Returns an empty string if a null String object is passed through.
     * @param string The string to validate.
     * @return The validated string if it is not null, or an empty string otherwise.
     * @see #defaultStringIfNull(String, String)
     */
    public static String emptyStringIfNull(final String string) {
        return string == null ? "" : string;
    }

    /**
     * Returns the first String if it is not null, the second String otherwise.
     * The second String object cannot be null!
     * @param string The possibly null String object
     * @param defaultString The non-null String object to use if the first String is null
     * @return The initial String if it is not null, the second String otherwise
     * @throws NullPointerException When the second String object is null
     */
    public static String defaultStringIfNull(final String string, final String defaultString) {
        if (defaultString == null) {
            throw new NullPointerException(DEFAULT_NULL_STR_MESSAGE);
        }
        return string == null ? defaultString : string;
    }
}
