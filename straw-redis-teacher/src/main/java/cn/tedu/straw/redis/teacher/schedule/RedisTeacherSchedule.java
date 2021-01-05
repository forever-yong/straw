package cn.tedu.straw.redis.teacher.schedule;


import cn.tedu.straw.commons.vo.TeacherVO;
import cn.tedu.straw.redis.teacher.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class RedisTeacherSchedule {
    @Autowired
    IUserService userService;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @Scheduled(fixedRate = 15 * 1000)
    public void updateTags() {
        // 从MySQL中读取标签列表
        List<TeacherVO> teacherTags = userService.getTeacherList();
        // 通过RedisTemplate获取List类型数据的操作器
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        // 在Redis中用于保存标签列表数据的Key
        String tagsKey = "teachers";
        // 先删除Redis服务器中已经保存的标签列表
        redisTemplate.delete(tagsKey);
        // 通过以上操作器向Redis中写入标签列表，每次只能写入List中的1条标签数据
        for (TeacherVO teacherTag : teacherTags) {
            ops.rightPush(tagsKey, teacherTag);
        }
    }
}