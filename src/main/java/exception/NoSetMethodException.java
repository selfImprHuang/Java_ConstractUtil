package exception;
/**
 * @author 志军
 */
public class NoSetMethodException extends RuntimeException {

    public NoSetMethodException() {
        super();
    }

    public NoSetMethodException(String message) {
        super(message);
    }

    public NoSetMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSetMethodException(Throwable cause) {
        super(cause);
    }

    protected NoSetMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
