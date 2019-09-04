package net.vanabel.vanabelutilities.validator;

/**
 * A class that holds a collection of methods that are used for validating various cases regarding any object.
 */
public class ObjectValidator {

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
