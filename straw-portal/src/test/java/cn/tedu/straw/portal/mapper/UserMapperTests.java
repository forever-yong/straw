package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.model.User;
import cn.tedu.straw.portal.vo.TeacherVO;
import cn.tedu.straw.portal.vo.UserLoginVO;
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

    @Test
    void findLoginVOByPhone() {
        String phone = "18371232737";
        UserLoginVO user = mapper.findLoginVOByPhone(phone);
        System.out.println("UserLoginVO >>> " + user);
    }

    @Test
    void findByPhone(){
        String phone = "13800138001";
       User user= mapper.findByPhone(phone);
       System.out.println("User >>>"+user);
    }
    @Test
    void insert(){
        User user = new User();
        user.setUsername("white");
        user.setPassword("320320");
        int rows = mapper.insert(user);
        System.out.println("rows = "+rows);

    }

    @Test
    void deleteById(){
        int id = 1;
        int rows = mapper.deleteById(id);
        System.out.println("rows = "+rows);
    }

    @Test
    void selectById(){
        int id = 2;
        User user = mapper.selectById(id);
        System.out.println("User >>> "+user);
    }

    @Test
    void updateById(){
        User user = new User();
        user.setId(2);
        user.setGender(2);
        user.setPhone("2222222222");
        int rows= mapper.updateById(user);
        System.out.println("rows >>> "+rows);
    }
    @Test
    void selectList(){

        List<User> users =mapper.selectList(null);
        System.out.println("User List  Size ="+users.size());
        for (User user : users) {
            System.out.println("User >>> "+user);
        }
    }

    @Test
    void selectQueryWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("id",3);
        //queryWrapper.lt("id",4);
        queryWrapper.getSqlSelect();
        List<User> users =mapper.selectList(queryWrapper);
        System.out.println("User List  Size ="+users.size());
        for (User user : users) {
            System.out.println("User >>> "+user);
        }
    }
}
