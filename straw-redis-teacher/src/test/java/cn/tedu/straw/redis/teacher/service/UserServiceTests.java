package cn.tedu.straw.redis.teacher.service;

import cn.tedu.straw.commons.vo.TeacherVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    IUserService service;

    @Test
    void getTeacherList() {
        List<TeacherVO> teachers = service.getTeacherList();
        System.out.println("Teacher List Size = " + teachers.size());
        for (TeacherVO teacher : teachers) {
            System.out.println("Teacher >>> " + teacher);
        }
    }

}
