package cn.tedu.straw.redis.tag.schedule;

import cn.tedu.straw.commons.vo.TagVO;
import cn.tedu.straw.redis.tag.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
@Component
public class RedisTagSchedule {
    @Autowired
    ITagService tagService;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @Scheduled(fixedRate = 15 * 1000)
    public void updateTags() {
        // 从MySQL中读取标签列表
        List<TagVO> tags = tagService.getTagList();

        // 通过RedisTemplate获取List类型数据的操作器
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        // 通过RedisTemplate获取一般类型类型数据的操作器
        ValueOperations<String,Serializable> opsForValue = redisTemplate.opsForValue();
        // 在Redis中用于保存标签列表数据的Key
        String tagsKey = "tags";
        // 先删除Redis服务器中已经保存的标签列表
        redisTemplate.delete(tagsKey);
        // 通过以上操作器向Redis中写入标签列表，每次只能写入List中的1条标签数据
        for (TagVO tag : tags) {
            //向List中存入当前标签数据
            opsForList.rightPush(tagsKey, tag);
            //将当前标签数据单独存为Redis中的一个数据
            String key = "tag"+tag.getId();
            opsForValue.set(key,tag);
        }
    }
}