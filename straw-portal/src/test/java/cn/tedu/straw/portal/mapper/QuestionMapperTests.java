package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.vo.QuestionSimpleListItemVO;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QuestionMapperTests {

    @Autowired
    QuestionMapper mapper;

    @Test
    void findByUserId() {
        int pageNum=2;//页码
        int pageSize=2;//每页显示几条数据
        PageHelper.startPage(pageNum,pageSize);
        Integer userId = 1;
        List<Question> questions = mapper.findByUserId(userId);
        System.out.println("Question Count = " + questions.size());
        for (Question question : questions) {
            System.out.println("Question >>> " + question);
        }
    }

    @Test
    void findMostHitsList() {
        List<QuestionSimpleListItemVO> questions = mapper.findMostHitsList();
        System.out.println("Question Count = " + questions.size());
        for (QuestionSimpleListItemVO question : questions) {
            System.out.println("Question >>> " + question);
        }
    }

    @Test
    void findById() {
        Integer id = 2;
        Question question = mapper.findById(id);
        System.out.println("Question >>> " + question);
    }
}
