package cn.tedu.straw.elasticsearch.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "question")
public class QuestionSearchVO implements Serializable {
    @Id
    private Integer id;
    @Field(type= FieldType.Text,analyzer="ik_smart",searchAnalyzer ="ik_smart" )
    private String title;
    @Field(type= FieldType.Text,analyzer="ik_smart",searchAnalyzer ="ik_smart" )
    private String content;
    @Field(type= FieldType.Integer)
    private Integer userId;
    @Field(type= FieldType.Keyword)
    private String userNickName;
}