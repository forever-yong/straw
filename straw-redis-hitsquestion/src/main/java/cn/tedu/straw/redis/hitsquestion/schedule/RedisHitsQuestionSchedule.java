package cn.tedu.straw.redis.hitsquestion.schedule;

import cn.tedu.straw.commons.vo.TagVO;
import cn.tedu.straw.redis.hitsquestion.service.IQuestionService;
import cn.tedu.straw.redis.hitsquestion.vo.QuestionSimpleListItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class RedisHitsQuestionSchedule {
    @Autowired
    IQuestionService questionService;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @Scheduled(fixedRate = 60*1000)
    public void updateHitsQuestion(){
        // 从MySQL中读取热点问题列表
        List<QuestionSimpleListItemVO> hitsquestions = questionService.getMostHitsList();

        // 通过RedisTemplate获取List类型数据的操作器
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        // 通过RedisTemplate获取一般类型类型数据的操作器
        ValueOperations<String,Serializable> opsForValue = redisTemplate.opsForValue();
        // 在Redis中用于保存热点问题列表数据的Key
        String hitsquestionKey = "hitsQuestion";
        // 先删除Redis服务器中已经保存的热点问题列表
        redisTemplate.delete(hitsquestionKey);
        // 通过以上操作器向Redis中写入热点问题列表，每次只能写入List中的1条标签数据
        for (QuestionSimpleListItemVO hitsquestion : hitsquestions) {
            //向List中存入当前热点问题数据
            opsForList.rightPush(hitsquestionKey, hitsquestion);
            //将当前热点问题数据单独存为Redis中的一个数据
            String key = "hitsquestion"+hitsquestion.getId();
            opsForValue.set(key,hitsquestion);
        }
    }
}
