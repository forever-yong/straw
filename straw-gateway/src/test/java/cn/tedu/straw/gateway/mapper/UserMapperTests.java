package cn.tedu.straw.gateway.mapper;

import cn.tedu.straw.gateway.vo.UserLoginVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void findLoginVOByPhone() {
        String phone = "99999999999";
        UserLoginVO user = mapper.findLoginVOByPhone(phone);
        System.out.println("UserLoginVO >>> " + user);
    }


}
