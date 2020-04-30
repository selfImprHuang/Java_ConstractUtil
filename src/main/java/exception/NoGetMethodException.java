package exception;

public class NoGetMethodException  extends  RuntimeException{

    public NoGetMethodException() {
        super();
    }

    public NoGetMethodException(String message) {
        super(message);
    }

    public NoGetMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoGetMethodException(Throwable cause) {
        super(cause);
    }

    protected NoGetMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
