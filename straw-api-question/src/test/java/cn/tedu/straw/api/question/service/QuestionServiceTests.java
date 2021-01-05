package cn.tedu.straw.api.question.service;

import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.api.question.vo.QuestionListItemVO;
import cn.tedu.straw.commons.ex.ServiceException;
import cn.tedu.straw.portal.model.Question;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class QuestionServiceTests {
    @Autowired
    IQuestionService service;
    @Test
    void post(){
        try {
            PostQuestionDTO postQuestionDTO = new PostQuestionDTO();
            postQuestionDTO.setTitle("测试分布式微服务项目第2个问题");
            postQuestionDTO.setContent("第2个问题，还是不知道要问题什么……");
            postQuestionDTO.setTagIds(new Integer[]{1, 3, 4});
            postQuestionDTO.setTeacherIds(new Integer[]{4, 5, 6});
            Integer userId = 7;
            String userNickName = "皮蛋超人";
            service.post(postQuestionDTO, userId, userNickName);
            System.out.println("发布问题成功");
        }catch (ServiceException e){
            System.out.println("发布问题失败!");
            System.out.println("错误类型:"+e.getClass().getName());
            System.out.println("错误原因:"+e.getMessage());
        }
    }

    @Test
    void getMyQuestions(){
        Integer userId = 1;
        Integer pageNum=3;
        PageInfo<QuestionListItemVO> pageInfo = service.getMyQuestions(userId,pageNum);
        System.out.println("pageInfo >>>"+pageInfo);
    }

}
