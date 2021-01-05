package cn.tedu.straw.elasticsearch.mapper;

import cn.tedu.straw.elasticsearch.vo.QuestionSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {
    List<QuestionSearchVO> findQuestions();
}