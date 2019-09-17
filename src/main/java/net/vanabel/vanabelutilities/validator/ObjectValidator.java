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
 * A class that holds a collection of static methods that are used for validating various cases regarding any object.
 */
public class ObjectValidator {

    ////////////////////////////////////////
    //   Private static fields
    //////////////////////////////////////

    private final static String DEFAULT_NULL_MESSAGE = "A null object cannot be passed here!";


    ////////////////////////////////////////
    //   Non-null object checks
    //////////////////////////////////////

    /**
     * Quickly checks if an object is null.
     * If the object is null, an exception is thrown.
     * @param object The object to check.
     * @param <T> The object type of the checked object.
     * @return The object, provided that it is not null.
     * @throws NullPointerException When the object is null.
     * @see #isNotNull(Object, String)
     */
    public static <T> T isNotNull(final T object) {
        return isNotNull(object, DEFAULT_NULL_MESSAGE);
    }

    /**
     * Quickly checks if an object is null.
     * If the object is null, an exception is thrown.
     * @param object The object to check.
     * @param message The exception message if the object is null.
     * @param <T> The object type of the checked object.
     * @return The object, provided that it is not null.
     * @throws NullPointerException When the object is null.
     */
    public static <T> T isNotNull(final T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
