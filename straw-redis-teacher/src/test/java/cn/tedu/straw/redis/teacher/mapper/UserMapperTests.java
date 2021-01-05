package cn.tedu.straw.redis.teacher.mapper;


import cn.tedu.straw.commons.vo.TeacherVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void findAllTeachers() {
        List<TeacherVO> teachers = mapper.findAllTeachers();
        System.out.println("Teacher List Size = " + teachers.size());
        for (TeacherVO teacher : teachers) {
            System.out.println("Teacher >>> " + teacher);
        }
    }

}
