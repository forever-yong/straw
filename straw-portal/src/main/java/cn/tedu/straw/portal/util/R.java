package cn.tedu.straw.portal.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class R<E> {
    private Integer state;
    private String message;
    private E data;

    public static R ok(){
        return new R().setState(State.OK);
    }

    public static <E> R ok(E data){
        return new R().setState(State.OK).setData(data);
    }

    public static R failure(Integer state,String message){
        return new R().setState(state).setMessage(message);
    }

    public static R failure(Integer state, Throwable e){
        return new R().setState(state).setMessage(e.getMessage());
    }

    public interface State{
        int OK = 2000;
        int ERR_INVITE_CODE = 4010;
        int ERR_CLASS_DISABLED = 4020;
        int ERR_PHONE_DUPLICATE = 4030;
        int ERR_INSERT = 4040;
        int ERR_PARAM_VALIDATION=4050;

        int ERR_FILE_UPLOAD_EMPTY=5010;
        int ERR_FILE_UPLOAD_SIZE=5020;
        int ERR_FILE_UPLOAD_TYPE=5030;
        int ERR_FILE_UPLOAD_IO=5040;
        int ERR_FILE_UPLOAD_STATE=5050;

        int ERR_QUESTION_NOT_FOUND=6010;

        int ERR_ANSWER_NOT_FOUND=7010;
    }
}
