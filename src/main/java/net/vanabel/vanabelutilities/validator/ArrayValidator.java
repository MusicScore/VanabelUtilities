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
 * A class that holds a collection of static methods that are used for validating various cases regarding arrays.
 */
public class ArrayValidator {

    ////////////////////////////////////////
    //   Static fields
    //////////////////////////////////////

    private static String DEFAULT_NULL_ARRAY_MSG = "The array cannot be null!";
    private static String DEFAULT_EMPTY_ARRAY_MSG = "The array cannot be empty!";


    ////////////////////////////////////////
    //   Empty/null methods
    //////////////////////////////////////

    /**
     * Quickly checks if the array is empty or null.
     * If the array is empty or null, then an exception is thrown.
     * @param data The array of bytes
     * @return The array if the array is not empty and not null
     * @throws NullPointerException When the array is null
     * @throws IllegalArgumentException When the array is empty
     */
    public static byte[] notEmptyOrNull(final byte[] data) {
        if (data == null) {
            throw new NullPointerException(DEFAULT_NULL_ARRAY_MSG);
        }
        if (data.length == 0) {
            throw new IllegalArgumentException(DEFAULT_EMPTY_ARRAY_MSG);
        }
        return data;
    }

    /**
     * Quickly checks if the array is empty or null.
     * If the array is empty or null, then an exception is thrown.
     * @param data The array of shorts
     * @return The array if the array is not empty and not null
     * @throws NullPointerException When the array is null
     * @throws IllegalArgumentException When the array is empty
     */
    public static short[] notEmptyOrNull(final short[] data) {
        if (data == null) {
            throw new NullPointerException(DEFAULT_NULL_ARRAY_MSG);
        }
        if (data.length == 0) {
            throw new IllegalArgumentException(DEFAULT_EMPTY_ARRAY_MSG);
        }
        return data;
    }

    /**
     * Quickly checks if the array is empty or null.
     * If the array is empty or null, then an exception is thrown.
     * @param data The array of integers
     * @return The array if the array is not empty and not null
     * @throws NullPointerException When the array is null
     * @throws IllegalArgumentException When the array is empty
     */
    public static int[] notEmptyOrNull(final int[] data) {
        if (data == null) {
            throw new NullPointerException(DEFAULT_NULL_ARRAY_MSG);
        }
        if (data.length == 0) {
            throw new IllegalArgumentException(DEFAULT_EMPTY_ARRAY_MSG);
        }
        return data;
    }

    /**
     * Quickly checks if the array is empty or null.
     * If the array is empty or null, then an exception is thrown.
     * @param data The array of longs
     * @return The array if the array is not empty and not null
     * @throws NullPointerException When the array is null
     * @throws IllegalArgumentException When the array is empty
     */
    public static long[] notEmptyOrNull(final long[] data) {
        if (data == null) {
            throw new NullPointerException(DEFAULT_NULL_ARRAY_MSG);
        }
        if (data.length == 0) {
            throw new IllegalArgumentException(DEFAULT_EMPTY_ARRAY_MSG);
        }
        return data;
    }

    /**
     * Quickly checks if the array is empty or null.
     * If the array is empty or null, then an exception is thrown.
     * @param data The array of doubles
     * @return The array if the array is not empty and not null
     * @throws NullPointerException When the array is null
     * @throws IllegalArgumentException When the array is empty
     */
    public static double[] notEmptyOrNull(final double[] data) {
        if (data == null) {
            throw new NullPointerException(DEFAULT_NULL_ARRAY_MSG);
        }
        if (data.length == 0) {
            throw new IllegalArgumentException(DEFAULT_EMPTY_ARRAY_MSG);
        }
        return data;
    }

    /**
     * Quickly checks if the array is empty or null.
     * If the array is empty or null, then an exception is thrown.
     * @param data The array of floats
     * @return The array if the array is not empty and not null
     * @throws NullPointerException When the array is null
     * @throws IllegalArgumentException When the array is empty
     */
    public static float[] notEmptyOrNull(final float[] data) {
        if (data == null) {
            throw new NullPointerException(DEFAULT_NULL_ARRAY_MSG);
        }
        if (data.length == 0) {
            throw new IllegalArgumentException(DEFAULT_EMPTY_ARRAY_MSG);
        }
        return data;
    }

    /**
     * Quickly checks if the array is empty or null.
     * If the array is empty or null, then an exception is thrown.
     * @param data The array of Objects
     * @param <T> Any Object
     * @return The array if the array is not empty and not null
     * @throws NullPointerException When the array is null
     * @throws IllegalArgumentException When the array is empty
     */
    public static <T> T[] notEmptyOrNull(final T[] data) {
        if (data == null) {
            throw new NullPointerException(DEFAULT_NULL_ARRAY_MSG);
        }
        if (data.length == 0) {
            throw new IllegalArgumentException(DEFAULT_EMPTY_ARRAY_MSG);
        }
        return data;
    }
}
