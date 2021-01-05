package cn.tedu.straw.api.question.mapper;

import cn.tedu.straw.api.question.vo.QuestionListItemVO;
import cn.tedu.straw.commons.model.Question;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QuestionMapperTests {

    @Autowired
    QuestionMapper mapper;

    @Test
    void insert(){
        Question question = new Question();
        question.setTitle("测试分布式的微服务项目的发布问题-1");
        question.setContent("测试正文");
        question.setUserId(7);
        question.setUserNickName("苍老师");
        question.setStatus(0);
        question.setHits(0);
        int rowws = mapper.insert(question);
        System.out.println("rows ="+rowws);
    }

    @Test
    void findByUserId() {
        int pageNum = 1; // 页码
        int pageSize = 3; // 每页显示几条数据
        PageHelper.startPage(pageNum, pageSize);

        Integer userId = 7;
        List<QuestionListItemVO> questions = mapper.findByUserId(userId);

        PageInfo<QuestionListItemVO> pageInfo = new PageInfo<>(questions);
        System.out.println("PageInfo >>> " + pageInfo);
    }
}
