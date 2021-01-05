package cn.tedu.straw.portal.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AnswerVO implements Serializable {
    private Integer id;
    private String content;
    private Integer userId;
    private String userNickName;
    private Integer isAccepted;
    private LocalDateTime gmtCreate;
    private List<CommentVO> comments;
}
