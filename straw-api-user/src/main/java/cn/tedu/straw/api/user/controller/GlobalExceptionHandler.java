package cn.tedu.straw.api.user.controller;

import cn.tedu.straw.commons.ex.*;
import cn.tedu.straw.commons.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ServiceException.class})
    public R handleException(Throwable e){
        R r = new R();
        r.setMessage(e.getMessage());

        if(e instanceof InviteCodeException){
            return R.failure(R.State.ERR_INVITE_CODE,e);
        }else if(e instanceof ClassDisabledException){
            return R.failure(R.State.ERR_CLASS_DISABLED,e);
        }else if (e instanceof PhoneDuplicateException){
            return R.failure(R.State.ERR_PHONE_DUPLICATE,e);
        }else if(e instanceof InsertException){
            return R.failure(R.State.ERR_INSERT,e);
        }else if (e instanceof ParamValidationException){
            return R.failure(R.State.ERR_PARAM_VALIDATION,e);
        }
        return null;
    }
}
