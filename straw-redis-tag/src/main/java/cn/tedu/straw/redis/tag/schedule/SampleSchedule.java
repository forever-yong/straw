package cn.tedu.straw.redis.tag.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Deprecated
//@Component
public class SampleSchedule {
    @Scheduled(fixedRate = 1*1000)
    public void test(){
        System.out.println(LocalDateTime.now()+" >>>正在执行计划任务......");
    }
}
