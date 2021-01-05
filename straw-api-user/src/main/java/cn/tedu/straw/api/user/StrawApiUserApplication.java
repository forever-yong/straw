package cn.tedu.straw.api.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.tedu.straw.api.user.mapper")
public class StrawApiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrawApiUserApplication.class, args);
    }

}
