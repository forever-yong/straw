package cn.tedu.straw.api.question.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PostQuestionDTO implements Serializable {
    @NotNull(message = "发布问题失败！必须填写问题的标题！")
    @Size(min = 3, max = 50, message = "发布问题失败！问题的标题必须是3-50个字符！")
    private String title;
    @NotNull(message = "发布问题失败！必须填写问题的正文！")
    private String content;
    @NotNull(message = "发布问题失败！必须提交问题的标签！")
    private Integer[] tagIds;
    @NotNull(message = "发布问题失败！必须选择回答问题的老师！")
    private Integer[] teacherIds;
}
