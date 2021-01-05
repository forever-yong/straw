package cn.tedu.straw.redis.hitsquestion.service;

import cn.tedu.straw.redis.hitsquestion.vo.QuestionSimpleListItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QuestionServiceTests {
    @Autowired
    IQuestionService service;


    @Test
    void getMostHitsList() {
        List<QuestionSimpleListItemVO> questions = service.getMostHitsList();
        System.out.println("Question Count = " + questions.size());
        for (QuestionSimpleListItemVO question : questions) {
            System.out.println("Question >>> " + question);
        }
    }




}
