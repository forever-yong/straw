package cn.tedu.straw.api.user.mapper;


import cn.tedu.straw.commons.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void insert(){
        User user = new User();
        user.setUsername("white");
        user.setPassword("320320");
        int rows = mapper.insert(user);
        System.out.println("rows = "+rows);

    }

}
