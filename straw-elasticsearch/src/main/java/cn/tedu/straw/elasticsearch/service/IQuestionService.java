package cn.tedu.straw.elasticsearch.service;

import cn.tedu.straw.elasticsearch.vo.QuestionSearchVO;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

public interface IQuestionService {

    PageInfo<QuestionSearchVO> getQuestionListFromDatabase(Integer pageNum);

    Page<QuestionSearchVO> search(String  keyword, Integer pageNum);

}