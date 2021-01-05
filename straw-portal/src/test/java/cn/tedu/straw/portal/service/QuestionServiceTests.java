package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.dto.PostQuestionDTO;
import cn.tedu.straw.portal.ex.ServiceException;
import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.vo.QuestionSimpleListItemVO;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QuestionServiceTests {
    @Autowired
    IQuestionService service;
    @Test
    void post(){
        try {
            PostQuestionDTO postQuestionDTO = new PostQuestionDTO();
            postQuestionDTO.setTitle("测试第三个问题");
            postQuestionDTO.setContent("第3个问题，还是不知道要问题什么……");
            postQuestionDTO.setTagIds(new Integer[]{1, 3, 4});
            postQuestionDTO.setTeacherIds(new Integer[]{4, 5, 6});
            Integer userId = 5;
            String userNickName = "咸蛋超人";
            service.post(postQuestionDTO, userId, userNickName);
            System.out.println("发布问题成功");
        }catch (ServiceException e){
            System.out.println("发布问题失败!");
            System.out.println("错误类型:"+e.getClass().getName());
            System.out.println("错误原因:"+e.getMessage());
        }
    }

    @Test
    void getMostHitsList() {
        List<QuestionSimpleListItemVO> questions = service.getMostHitsList();
        System.out.println("Question Count = " + questions.size());
        for (QuestionSimpleListItemVO question : questions) {
            System.out.println("Question >>> " + question);
        }
    }

    @Test
    void getMyQuestions(){
        Integer userId = 1;
        Integer pageNum=3;
        PageInfo<Question> pageInfo = service.getMyQuestions(userId,pageNum);
        System.out.println("pageInfo >>>"+pageInfo);
    }

    @Test
    void getQuestionDetails() {
        try {
            Integer id = 700;
            Question question = service.getQuestionDetails(id);
            System.out.println("Question >>> " + question);
        } catch (ServiceException e) {
            System.out.println("错误类型：" + e.getClass().getName());
            System.out.println("错误原因：" + e.getMessage());
        }
    }

}
