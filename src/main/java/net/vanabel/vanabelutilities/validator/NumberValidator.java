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
 * A class that holds a collection of static methods that are used for validating various cases regarding numeric data types.
 */
public class NumberValidator {

    ////////////////////////////////////////
    //   Private fields
    //////////////////////////////////////

    private static String DEFAULT_NON_POSITIVE_MSG = "This number is not positive!";
    private static String DEFAULT_NEGATIVE_MSG = "This number cannot be negative!";
    private static String[] DEFAULT_NOT_IN_INTERVAL_MSG = {
            "This number is not between ",
            " and "
    };
    private static String DEFAULT_MALFORMED_INTERVAL_MSG = "The right end of the range must not be less than the left end!";


    ////////////////////////////////////////
    //   Private static methods
    //////////////////////////////////////

    private static String defMalformedIntervalMsgConstr(byte i, byte f) {
        return DEFAULT_NOT_IN_INTERVAL_MSG[0] + i + DEFAULT_NOT_IN_INTERVAL_MSG[1] + f;
    }

    private static String defMalformedIntervalMsgConstr(short i, short f) {
        return DEFAULT_NOT_IN_INTERVAL_MSG[0] + i + DEFAULT_NOT_IN_INTERVAL_MSG[1] + f;
    }

    private static String defMalformedIntervalMsgConstr(int i, int f) {
        return DEFAULT_NOT_IN_INTERVAL_MSG[0] + i + DEFAULT_NOT_IN_INTERVAL_MSG[1] + f;
    }

    private static String defMalformedIntervalMsgConstr(long i, long f) {
        return DEFAULT_NOT_IN_INTERVAL_MSG[0] + i + DEFAULT_NOT_IN_INTERVAL_MSG[1] + f;
    }

    private static String defMalformedIntervalMsgConstr(double i, double f) {
        return DEFAULT_NOT_IN_INTERVAL_MSG[0] + i + DEFAULT_NOT_IN_INTERVAL_MSG[1] + f;
    }

    private static String defMalformedIntervalMsgConstr(float i, float f) {
        return DEFAULT_NOT_IN_INTERVAL_MSG[0] + i + DEFAULT_NOT_IN_INTERVAL_MSG[1] + f;
    }


    ////////////////////////////////////////
    //   Positive number checks
    //////////////////////////////////////

    /**
     * Quickly checks if the byte is a positive number.
     * If the byte is non-positive, an exception is thrown.
     * @param bNum The byte to check
     * @return The byte, provided that it is positive
     * @throws IllegalArgumentException When the byte is non-positive
     * @see #isPositive(byte, String)
     */
    public static byte isPositive(byte bNum) {
        return isPositive(bNum, null);
    }

    /**
     * Quickly checks if the short is a positive number.
     * If the short is non-positive, an exception is thrown.
     * @param sNum The short to check
     * @return The short, provided that it is positive
     * @throws IllegalArgumentException When the short is non-positive
     * @see #isPositive(short, String)
     */
    public static short isPositive(short sNum) {
        return isPositive(sNum, null);
    }

    /**
     * Quickly checks if the integer is a positive number.
     * If the integer is non-positive, an exception is thrown.
     * @param iNum The integer to check
     * @return The integer, provided that it is positive
     * @throws IllegalArgumentException When the integer is non-positive
     * @see #isPositive(int, String)
     */
    public static int isPositive(int iNum) {
        return isPositive(iNum, null);
    }

    /**
     * Quickly checks if the long is a positive number.
     * If the long is non-positive, an exception is thrown.
     * @param lNum The long to check
     * @return The long, provided that it is positive
     * @throws IllegalArgumentException When the long is non-positive
     * @see #isPositive(long, String)
     */
    public static long isPositive(long lNum) {
        return isPositive(lNum, null);
    }

    /**
     * Quickly checks if the double is a positive number.
     * If the double is non-positive, an exception is thrown.
     * @param dNum The double to check
     * @return The double, provided that it is positive
     * @throws IllegalArgumentException When the double is non-positive
     * @see #isPositive(double, String)
     */
    public static double isPositive(double dNum) {
        return isPositive(dNum, null);
    }

    /**
     * Quickly checks if the float is a positive number.
     * If the float is non-positive, an exception is thrown.
     * @param fNum The float to check
     * @return The float, provided that it is positive
     * @throws IllegalArgumentException When the float is non-positive
     * @see #isPositive(float, String)
     */
    public static float isPositive(float fNum) {
        return isPositive(fNum, null);
    }

    /**
     * Quickly checks if the byte is a positive number.
     * If the byte is non-positive, an exception is thrown.
     * @param bNum The byte to check
     * @param message The exception message to use if the byte is non-positive
     * @return The byte, provided that it is positive
     * @throws IllegalArgumentException When the byte is non-positive
     */
    public static byte isPositive(byte bNum, String message) {
        if (bNum <= 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
        }
        return bNum;
    }

    /**
     * Quickly checks if the short is a positive number.
     * If the short is non-positive, an exception is thrown.
     * @param sNum The short to check
     * @param message The exception message to use if the short is non-positive
     * @return The short, provided that it is positive
     * @throws IllegalArgumentException When the short is non-positive
     */
    public static short isPositive(short sNum, String message) {
        if (sNum <= 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
        }
        return sNum;
    }

    /**
     * Quickly checks if the integer is a positive number.
     * If the integer is non-positive, an exception is thrown.
     * @param iNum The integer to check
     * @param message The exception message to use if the integer is non-positive
     * @return The integer, provided that it is positive
     * @throws IllegalArgumentException When the integer is non-positive
     */
    public static int isPositive(int iNum, String message) {
        if (iNum <= 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
        }
        return iNum;
    }

    /**
     * Quickly checks if the long is a positive number.
     * If the long is non-positive, an exception is thrown.
     * @param lNum The long to check
     * @param message The exception message to use if the long is non-positive
     * @return The long, provided that it is positive
     * @throws IllegalArgumentException When the long is non-positive
     */
    public static long isPositive(long lNum, String message) {
        if (lNum <= 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
        }
        return lNum;
    }

    /**
     * Quickly checks if the double is a positive number.
     * If the double is non-positive, an exception is thrown.
     * @param dNum The double to check
     * @param message The exception message to use if the double is non-positive
     * @return The double, provided that it is positive
     * @throws IllegalArgumentException When the double is non-positive
     */
    public static double isPositive(double dNum, String message) {
        if (dNum <= 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
        }
        return dNum;
    }

    /**
     * Quickly checks if the float is a positive number.
     * If the float is non-positive, an exception is thrown.
     * @param fNum The float to check
     * @param message The exception message to use if the float is non-positive
     * @return The float, provided that it is positive
     * @throws IllegalArgumentException When the float is non-positive
     */
    public static float isPositive(float fNum, String message) {
        if (fNum <= 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
        }
        return fNum;
    }


    ////////////////////////////////////////
    //   Non-negative number checks
    //////////////////////////////////////

    /**
     * Quickly checks if the byte is non-negative.
     * If the byte is negative, an exception is thrown.
     * @param bNum The byte to check
     * @return The byte, provided that it's not negative
     * @throws IllegalArgumentException When the byte is negative
     * @see #nonNegative(byte, String)
     */
    public static byte nonNegative(byte bNum) {
        return nonNegative(bNum, null);
    }

    /**
     * Quickly checks if the short is non-negative.
     * If the short is negative, an exception is thrown.
     * @param sNum The short to check
     * @return The short, provided that it's not negative
     * @throws IllegalArgumentException When the short is negative
     * @see #nonNegative(short, String)
     */
    public static short nonNegative(short sNum) {
        return nonNegative(sNum, null);
    }

    /**
     * Quickly checks if the integer is non-negative.
     * If the integer is negative, an exception is thrown.
     * @param iNum The byte to check
     * @return The integer, provided that it's not negative
     * @throws IllegalArgumentException When the integer is negative
     * @see #nonNegative(int, String)
     */
    public static int nonNegative(int iNum) {
        return nonNegative(iNum, null);
    }

    /**
     * Quickly checks if the long is non-negative.
     * If the long is negative, an exception is thrown.
     * @param lNum The long to check
     * @return The long, provided that it's not negative
     * @throws IllegalArgumentException When the long is negative
     * @see #nonNegative(long, String)
     */
    public static long nonNegative(long lNum) {
        return nonNegative(lNum, null);
    }

    /**
     * Quickly checks if the double is non-negative.
     * If the double is negative, an exception is thrown.
     * @param dNum The double to check
     * @return The double, provided that it's not negative
     * @throws IllegalArgumentException When the double is negative
     * @see #nonNegative(double, String)
     */
    public static double nonNegative(double dNum) {
        return nonNegative(dNum, null);
    }

    /**
     * Quickly checks if the float is non-negative.
     * If the float is negative, an exception is thrown.
     * @param fNum The float to check
     * @return The float, provided that it's not negative
     * @throws IllegalArgumentException When the float is negative
     * @see #nonNegative(float, String)
     */
    public static float nonNegative(float fNum) {
        return nonNegative(fNum, null);
    }

    /**
     * Quickly checks if the byte is non-negative.
     * If the byte is negative, an exception is thrown.
     * @param bNum The byte to check
     * @param message The exception message to use if the byte is negative
     * @return The byte, provided that it's not negative
     * @throws IllegalArgumentException When the byte is negative
     */
    public static byte nonNegative(byte bNum, String message) {
        if (bNum < 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NEGATIVE_MSG));
        }
        return bNum;
    }

    /**
     * Quickly checks if the short is non-negative.
     * If the short is negative, an exception is thrown.
     * @param sNum The short to check
     * @param message The exception message to use if the short is negative
     * @return The short, provided that it's not negative
     * @throws IllegalArgumentException When the short is negative
     */
    public static short nonNegative(short sNum, String message) {
        if (sNum < 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NEGATIVE_MSG));
        }
        return sNum;
    }

    /**
     * Quickly checks if then integer is non-negative.
     * If the integer is negative, an exception is thrown.
     * @param iNum The byte to check
     * @param message The exception message to use if the integer is negative
     * @return The integer, provided that it's not negative
     * @throws IllegalArgumentException When the integer is negative
     */
    public static int nonNegative(int iNum, String message) {
        if (iNum < 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NEGATIVE_MSG));
        }
        return iNum;
    }

    /**
     * Quickly checks if the long is non-negative.
     * If the long is negative, an exception is thrown.
     * @param lNum The long to check
     * @param message The exception message to use if the long is negative
     * @return The long, provided that it's not negative
     * @throws IllegalArgumentException When the long is negative
     */
    public static long nonNegative(long lNum, String message) {
        if (lNum < 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NEGATIVE_MSG));
        }
        return lNum;
    }

    /**
     * Quickly checks if the double is non-negative.
     * If the double is negative, an exception is thrown.
     * @param dNum The double to check
     * @param message The exception message to use if the double is negative
     * @return The double, provided that it's not negative
     * @throws IllegalArgumentException When the double is negative
     */
    public static double nonNegative(double dNum, String message) {
        if (dNum < 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NEGATIVE_MSG));
        }
        return dNum;
    }

    /**
     * Quickly checks if the float is non-negative.
     * If the float is negative, an exception is thrown.
     * @param fNum The float to check
     * @param message The exception message to use if the float is negative
     * @return The float, provided that it's not negative
     * @throws IllegalArgumentException When the float is negative
     */
    public static float nonNegative(float fNum, String message) {
        if (fNum < 0) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NEGATIVE_MSG));
        }
        return fNum;
    }


    ////////////////////////////////////////
    //   Number is in interval checks
    //////////////////////////////////////

    /**
     * Quickly checks if the byte is in the specified interval, inclusively.
     * If the byte is not in the interval or if the interval is malformed, an exception is thrown.
     * @param bNum The byte to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @return The byte, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the byte is not in the interval
     * @see #isInInterval(byte, byte, byte, String)
     */
    public static byte isInInterval(byte bNum, byte left, byte right) {
        return isInInterval(bNum, left, right, null);
    }

    /**
     * Quickly checks if the short is in the specified interval, inclusively.
     * If the short is not in the interval or if the interval is malformed, an exception is thrown.
     * @param sNum The short to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @return The short, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the short is not in the interval
     * @see #isInInterval(short, short, short, String)
     */
    public static short isInInterval(short sNum, short left, short right) {
        return isInInterval(sNum, left, right, null);
    }

    /**
     * Quickly checks if the integer is in the specified interval, inclusively.
     * If the integer is not in the interval or if the interval is malformed, an exception is thrown.
     * @param iNum The integer to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @return The integer, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the integer is not in the interval
     * @see #isInInterval(int, int, int, String)
     */
    public static int isInInterval(int iNum, int left, int right) {
        return isInInterval(iNum, left, right, null);
    }

    /**
     * Quickly checks if the long is in the specified interval, inclusively.
     * If the long is not in the interval or if the interval is malformed, an exception is thrown.
     * @param lNum The long to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @return The long, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the long is not in the interval
     * @see #isInInterval(long, long, long, String)
     */
    public static long isInInterval(long lNum, long left, long right) {
        return isInInterval(lNum, left, right, null);
    }

    /**
     * Quickly checks if the double is in the specified interval, inclusively.
     * If the double is not in the interval or if the interval is malformed, an exception is thrown.
     * @param dNum The double to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @return The double, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the double is not in the interval
     * @see #isInInterval(double, double, double, String)
     */
    public static double isInInterval(double dNum, double left, double right) {
        return isInInterval(dNum, left, right, null);
    }

    /**
     * Quickly checks if the float is in the specified interval, inclusively.
     * If the float is not in the interval or if the interval is malformed, an exception is thrown.
     * @param fNum The float to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @return The float, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the float is not in the interval
     * @see #isInInterval(float, float, float, String)
     */
    public static float isInInterval(float fNum, float left, float right) {
        return isInInterval(fNum, left, right, null);
    }

    /**
     * Quickly checks if the byte is in the specified interval, inclusively.
     * If the byte is not in the interval or if the interval is malformed, an exception is thrown.
     * @param bNum The byte to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @param message The exception message to use if the byte is not in the interval
     * @return The byte, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the byte is not in the interval
     */
    public static byte isInInterval(byte bNum, byte left, byte right, String message) {
        if (right < left) {
            throw new IllegalArgumentException(DEFAULT_MALFORMED_INTERVAL_MSG);
        }
        if (bNum < left || bNum > right) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, defMalformedIntervalMsgConstr(left, right)));
        }
        return bNum;
    }

    /**
     * Quickly checks if the short is in the specified interval, inclusively.
     * If the short is not in the interval or if the interval is malformed, an exception is thrown.
     * @param sNum The short to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @param message The exception message to use if the short is not in the interval
     * @return The short, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the short is not in the interval
     */
    public static short isInInterval(short sNum, short left, short right, String message) {
        if (right < left) {
            throw new IllegalArgumentException(DEFAULT_MALFORMED_INTERVAL_MSG);
        }
        if (sNum < left || sNum > right) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, defMalformedIntervalMsgConstr(left, right)));
        }
        return sNum;
    }

    /**
     * Quickly checks if the integer is in the specified interval, inclusively.
     * If the integer is not in the interval or if the interval is malformed, an exception is thrown.
     * @param iNum The integer to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @param message The exception message to use if the integer is not in the interval
     * @return The integer, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the integer is not in the interval
     */
    public static int isInInterval(int iNum, int left, int right, String message) {
        if (right < left) {
            throw new IllegalArgumentException(DEFAULT_MALFORMED_INTERVAL_MSG);
        }
        if (iNum < left || iNum > right) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, defMalformedIntervalMsgConstr(left, right)));
        }
        return iNum;
    }

    /**
     * Quickly checks if the long is in the specified interval, inclusively.
     * If the long is not in the interval or if the interval is malformed, an exception is thrown.
     * @param lNum The long to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @param message The exception message to use if the long is not in the interval
     * @return The long, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the long is not in the interval
     */
    public static long isInInterval(long lNum, long left, long right, String message) {
        if (right < left) {
            throw new IllegalArgumentException(DEFAULT_MALFORMED_INTERVAL_MSG);
        }
        if (lNum < left || lNum > right) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, defMalformedIntervalMsgConstr(left, right)));
        }
        return lNum;
    }

    /**
     * Quickly checks if the double is in the specified interval, inclusively.
     * If the double is not in the interval or if the interval is malformed, an exception is thrown.
     * @param dNum The double to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @param message The exception message to use if the double is not in the interval
     * @return The double, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the double is not in the interval
     */
    public static double isInInterval(double dNum, double left, double right, String message) {
        if (right < left) {
            throw new IllegalArgumentException(DEFAULT_MALFORMED_INTERVAL_MSG);
        }
        if (dNum < left || dNum > right) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, defMalformedIntervalMsgConstr(left, right)));
        }
        return dNum;
    }

    /**
     * Quickly checks if the float is in the specified interval, inclusively.
     * If the float is not in the interval or if the interval is malformed, an exception is thrown.
     * @param fNum The float to check
     * @param left The left end of the interval
     * @param right The right end of the interval
     * @param message The exception message to use if the float is not in the interval
     * @return The float, provided that it's in the interval
     * @throws IllegalArgumentException When the interval is malformed or if the float is not in the interval
     */
    public static float isInInterval(float fNum, float left, float right, String message) {
        if (right < left) {
            throw new IllegalArgumentException(DEFAULT_MALFORMED_INTERVAL_MSG);
        }
        if (fNum < left || fNum > right) {
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, defMalformedIntervalMsgConstr(left, right)));
        }
        return fNum;
    }
}
