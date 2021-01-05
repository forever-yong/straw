package cn.tedu.straw.redis.hitsquestion.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionSimpleListItemVO implements Serializable {
    private Integer id;
    private String title;
    private Integer userId;
    private String userNickName;
    private Integer hits;
    private Integer status;
}
