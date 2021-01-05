package cn.tedu.straw.resource.controller;


import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.resource.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({FileUploadException.class})
    public R handleException(Throwable e){
        R r = new R();
        r.setMessage(e.getMessage());

        if(e instanceof FileEmptyException){
            return R.failure(R.State.ERR_FILE_UPLOAD_EMPTY,e);
        }else if(e instanceof FileSizeException){
            return R.failure(R.State.ERR_FILE_UPLOAD_SIZE,e);
        }else if(e instanceof FileTypeException){
            return R.failure(R.State.ERR_FILE_UPLOAD_TYPE,e);
        }else if(e instanceof FileUploadIOException){
            return R.failure(R.State.ERR_FILE_UPLOAD_IO,e);
        }else if(e instanceof FileStateException){
            return R.failure(R.State.ERR_FILE_UPLOAD_STATE,e);
        }
        return R.failure(-998,"有某个异常没有添加else...");
    }
}
