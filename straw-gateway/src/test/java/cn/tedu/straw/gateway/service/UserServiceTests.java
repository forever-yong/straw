package cn.tedu.straw.gateway.service;

import cn.tedu.straw.gateway.vo.UserLoginVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    IUserService service;



    @Test
    void getLoginUserInfo(){
        String phone = "99999999999";
        UserLoginVO user = service.getLoginUserInfo(phone);
        System.out.println("UserLoginVO >>>"+user);
    }
}
