package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.model.Answer;
import cn.tedu.straw.portal.vo.AnswerVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AnswerMapperTests {

    @Autowired
    AnswerMapper mapper;
    @Test
    void findByQuestionId() {
        Integer questionId = 5;
        List<AnswerVO> answers = mapper.findByQuestionId(questionId);
        System.out.println("Answer Count=" + answers.size());
        for (AnswerVO answer : answers) {
            System.out.println("Answer >>> " + answer);
        }
    }

    @Test
    void findById(){
        Integer id= 5;
        Answer answer = mapper.findById(id);
        System.out.println("Answer >>>"+answer);
    }
}
