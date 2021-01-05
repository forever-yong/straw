package cn.tedu.straw.api.question.controller;

import cn.tedu.straw.commons.ex.InsertException;
import cn.tedu.straw.commons.ex.ParamValidationException;
import cn.tedu.straw.commons.ex.ServiceException;
import cn.tedu.straw.commons.util.R;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ServiceException.class})
    public R handleException(Throwable e){
        R r = new R();
        r.setMessage(e.getMessage());

       if(e instanceof InsertException){
            return R.failure(R.State.ERR_INSERT,e);
        }else if(e instanceof ParamValidationException){
           return R.failure(R.State.ERR_PARAM_VALIDATION,e);
       }
        return R.failure(-998,"有某个异常没有添加else...");
    }
}
