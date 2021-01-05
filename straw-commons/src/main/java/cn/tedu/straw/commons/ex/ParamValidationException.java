package cn.tedu.straw.commons.ex;

public class ParamValidationException extends ServiceException {
    public ParamValidationException() {
    }

    public ParamValidationException(String message) {
        super(message);
    }

    public ParamValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamValidationException(Throwable cause) {
        super(cause);
    }

    public ParamValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
