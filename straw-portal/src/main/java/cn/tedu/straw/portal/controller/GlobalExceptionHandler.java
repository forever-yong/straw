package cn.tedu.straw.portal.controller;

import cn.tedu.straw.portal.ex.*;
import cn.tedu.straw.portal.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
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
        }else if(e instanceof FileEmptyException){
            return R.failure(R.State.ERR_FILE_UPLOAD_EMPTY,e);
        }else if(e instanceof FileSizeException){
            return R.failure(R.State.ERR_FILE_UPLOAD_SIZE,e);
        }else if(e instanceof FileTypeException){
            return R.failure(R.State.ERR_FILE_UPLOAD_TYPE,e);
        }else if(e instanceof FileUploadIOException){
            return R.failure(R.State.ERR_FILE_UPLOAD_IO,e);
        }else if(e instanceof FileStateException){
            return R.failure(R.State.ERR_FILE_UPLOAD_STATE,e);
        }else if(e instanceof QuestionNotFoundException){
            return R.failure(R.State.ERR_QUESTION_NOT_FOUND,e);
        }else if(e instanceof AnswerNotFoundException){
            return R.failure(R.State.ERR_ANSWER_NOT_FOUND,e);
        }
        return null;
    }
}
