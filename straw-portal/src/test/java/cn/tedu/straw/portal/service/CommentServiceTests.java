package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.dto.PostCommentDTO;
import cn.tedu.straw.portal.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentServiceTests {
    @Autowired
    ICommentService service;

    @Test
    void post() {
        try {
            PostCommentDTO postCommentDTO = new PostCommentDTO();
            postCommentDTO.setAnswerId(1);
            postCommentDTO.setContent("2号答案的第1个评论！");
            Integer userId = 25;
            String userNickName = "猪八戒";
            service.post(postCommentDTO, userId, userNickName);
            System.out.println("发表评论成功！");
        } catch (ServiceException e) {
            System.out.println("错误类型：" + e.getClass().getName());
            System.out.println("错误原因：" + e.getMessage());
        }
    }
}
