package exception;

/**
 * @author 志军
 */
public class TypeMisMatchException extends RuntimeException {

    public TypeMisMatchException() {
        super();
    }

    public TypeMisMatchException(String message) {
        super(message);
    }

    public TypeMisMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeMisMatchException(Throwable cause) {
        super(cause);
    }

    protected TypeMisMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
