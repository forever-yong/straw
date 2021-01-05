package cn.tedu.straw.portal.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CommentVO implements Serializable {

    private Integer id;
    private String content;
    private Integer userId;
    private String userNickName;
    private LocalDateTime gmtCreate;

}