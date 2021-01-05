package cn.tedu.straw.elasticsearch.service;

import cn.tedu.straw.elasticsearch.vo.QuestionSearchVO;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionServiceTests {

    @Autowired
    IQuestionService service;

    @Test
    void getQuestionListFromDatabase() {
        Integer pageNum = 1;
        PageInfo<QuestionSearchVO> questions = service.getQuestionListFromDatabase(pageNum);
        System.out.println("Question List >>> " + questions);
    }

}