package cn.tedu.straw.api.question.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PostAnswerDTO implements Serializable {

    @NotNull(message = "发表答案失败！缺少必要的参数！")
    private Integer questionId;

    @NotNull(message = "发表答案失败！缺少必要的参数！")
    @NotBlank(message = "发表答案失败！答案的正文不允许为空！")
    private String content;
}
