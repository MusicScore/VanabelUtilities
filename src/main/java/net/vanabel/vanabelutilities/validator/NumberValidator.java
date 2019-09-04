package net.vanabel.vanabelutilities.validator;

/**
 * A class that holds a collection of methods that are used for validating various cases regarding numeric data types.
 */
public class NumberValidator {

    private static String DEFAULT_NON_POSITIVE_MSG = "This number is not positive!";
    private static String DEFAULT_NEGATIVE_MSG = "This number cannot be negative!";


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
        return isPositive(bNum, DEFAULT_NON_POSITIVE_MSG);
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
        return isPositive(sNum, DEFAULT_NON_POSITIVE_MSG);
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
        return isPositive(iNum, DEFAULT_NON_POSITIVE_MSG);
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
        return isPositive(lNum, DEFAULT_NON_POSITIVE_MSG);
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
        return isPositive(dNum, DEFAULT_NON_POSITIVE_MSG);
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
        return isPositive(fNum, DEFAULT_NON_POSITIVE_MSG);
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
        return nonNegative(bNum, DEFAULT_NEGATIVE_MSG);
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
        return nonNegative(sNum, DEFAULT_NEGATIVE_MSG);
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
        return nonNegative(iNum, DEFAULT_NEGATIVE_MSG);
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
        return nonNegative(lNum, DEFAULT_NEGATIVE_MSG);
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
        return nonNegative(dNum, DEFAULT_NEGATIVE_MSG);
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
        return nonNegative(fNum, DEFAULT_NEGATIVE_MSG);
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
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
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
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
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
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
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
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
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
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
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
            throw new IllegalArgumentException(StringValidator.defaultStringIfNull(message, DEFAULT_NON_POSITIVE_MSG));
        }
        return fNum;
    }
}
