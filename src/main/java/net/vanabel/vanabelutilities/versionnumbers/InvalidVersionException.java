package net.vanabel.vanabelutilities.versionnumbers;

/**
 * A special exception for {@link Version}.
 */
public class InvalidVersionException extends RuntimeException {

    public InvalidVersionException() {
        super();
    }

    public InvalidVersionException(String message) {
        super(message);
    }

    public InvalidVersionException(String message, Throwable ex) {
        super(message, ex);
    }

    public InvalidVersionException(Throwable ex) {
        super(ex);
    }

    protected InvalidVersionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
