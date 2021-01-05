package cn.tedu.straw.api.question.message;

import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostQuestionMessage implements Serializable {

    private PostQuestionDTO postQuestionDTO;
    private Integer userId;
    private String userNickName;

}