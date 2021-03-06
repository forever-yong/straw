package cn.tedu.straw.portal.ex;

public class ClassDisabledException extends ServiceException{
    public ClassDisabledException() {
    }

    public ClassDisabledException(String message) {
        super(message);
    }

    public ClassDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassDisabledException(Throwable cause) {
        super(cause);
    }

    public ClassDisabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
