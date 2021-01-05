package cn.tedu.straw.elasticsearch.service.imp;

import cn.tedu.straw.elasticsearch.mapper.QuestionMapper;
import cn.tedu.straw.elasticsearch.repository.QuestionRepository;
import cn.tedu.straw.elasticsearch.service.IQuestionService;
import cn.tedu.straw.elasticsearch.vo.QuestionSearchVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Value("${project.question.query-from-database.page-size}")
    Integer queryPageSize;

    @Override
    public PageInfo<QuestionSearchVO> getQuestionListFromDatabase(Integer pageNum) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, queryPageSize);
        List<QuestionSearchVO> questions = questionMapper.findQuestions();
        return new PageInfo<>(questions);
    }

    @Autowired
    QuestionRepository questionRepository;
    @Value("${project.question.query-from-es.page-size}")
    Integer searchPageSize;
    @Override
    public Page<QuestionSearchVO> search(String keyword, Integer pageNum) {
        // 判断pageNum页码的有效性
        if(pageNum==null||pageNum<1){
            pageNum=1;
        }
        // 在Spring Data处理分页时，会使用0作为第1页的页码值，使用1作为第2页的页码值，以此类推
        pageNum--;
        // 准备分页对象
        Pageable pageable = PageRequest.of(pageNum,searchPageSize);
        // 执行搜索
        Page<QuestionSearchVO> questions = questionRepository.queryByTitleMatchesOrContentMatches(keyword,keyword,pageable);
        // 返回
        return questions;
    }

}