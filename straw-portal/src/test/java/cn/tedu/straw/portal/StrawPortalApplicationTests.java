package cn.tedu.straw.portal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class StrawPortalApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    DataSource dataSource;

    @Test
    void getConnection() throws SQLException {
        Connection conn = dataSource.getConnection();;
        System.out.println("Connection >>>"+conn);
    }
    @Test
    void md5() {
        String rawPassword = "0000000000";
        String encryptPassword = DigestUtils.md5DigestAsHex(rawPassword.getBytes());
        System.out.println("rawPassword=" + rawPassword);
        System.out.println("encryptPassword=" + encryptPassword);
    }

    @Test
    void bcryptEncode(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        System.out.println("rawPassword = "+rawPassword);

        for (int i = 0; i < 100; i++) {
            String encodedPassword = encoder.encode(rawPassword);
            System.out.println("encodedPassword ="+encodedPassword);
        }
    }
    @Test
    void bcryptMatcher(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encodedPassword="$2a$20$TFTUVJjU8SN/YR0xrAQqo.uj72octxlAr2Z76bpjtwZ27IwJpSkCu";
        System.out.println("rawPassword = "+rawPassword);
        System.out.println("encodedPassword ="+encodedPassword);
        boolean result = encoder.matches(rawPassword,encodedPassword);
        System.out.println("result = "+result);
    }
}
