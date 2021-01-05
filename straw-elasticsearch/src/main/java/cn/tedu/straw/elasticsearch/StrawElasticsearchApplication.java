package cn.tedu.straw.elasticsearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cn.tedu.straw.elasticsearch.mapper")
@EnableScheduling
public class StrawElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrawElasticsearchApplication.class, args);
    }

}
