package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.dto.PostAnswerDTO;
import cn.tedu.straw.portal.ex.ServiceException;
import cn.tedu.straw.portal.vo.AnswerVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AnswerServiceTests {
    @Autowired
    IAnswerService service;

    @Test
    void post() {
        try {
            PostAnswerDTO postAnswerDTO = new PostAnswerDTO();
            postAnswerDTO.setQuestionId(100);
            postAnswerDTO.setContent("测试回复5号问题的第1个答案");
            Integer userId = 9527;
            String userNickName = "天下第一二三";
            service.post(postAnswerDTO, userId, userNickName);
            System.out.println("发表答案成功！");
        } catch (ServiceException e) {
            System.out.println("错误类型：" + e.getClass().getName());
            System.out.println("错误原因：" + e.getMessage());
        }
    }

    @Test
    void getAnswerList() {
        Integer questionId = 5;
        List<AnswerVO> answers = service.getAnswerList(questionId);
        System.out.println("Answer Count=" + answers.size());
        for (AnswerVO answer : answers) {
            System.out.println("Answer >>> " + answer);
        }
    }
}
