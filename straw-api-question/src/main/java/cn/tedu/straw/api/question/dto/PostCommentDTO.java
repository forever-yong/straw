package cn.tedu.straw.api.question.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PostCommentDTO implements Serializable {

    @NotNull(message = "发表评论失败！缺少必要的参数！")
    private Integer answerId;

    @NotNull(message = "发表评论失败！缺少必要的参数！")
    @NotBlank(message = "发表评论失败！评论的正文不允许为空！")
    private String content;

}