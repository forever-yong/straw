package cn.tedu.straw.elasticsearch.mapper;

import cn.tedu.straw.elasticsearch.vo.QuestionSearchVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QuestionMapperTests {

    @Autowired
    QuestionMapper mapper;

    @Test
    void findQuestions() {
        List<QuestionSearchVO> questions = mapper.findQuestions();
        System.out.println("Question Count = " + questions.size());
        for (QuestionSearchVO question : questions) {
            System.out.println(">>> " + question);
        }
    }

}