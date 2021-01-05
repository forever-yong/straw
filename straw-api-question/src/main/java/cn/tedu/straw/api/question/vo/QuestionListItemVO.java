package cn.tedu.straw.api.question.vo;

import cn.tedu.straw.commons.vo.TagVO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionListItemVO implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private String userNickName;
    private Integer status;
    private Integer hits;
    private String tagIds;
    private LocalDateTime gmtCreate;
    private List<TagVO> tags;
}