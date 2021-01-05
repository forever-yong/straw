package cn.tedu.straw.redis.tag.controller;

import cn.tedu.straw.commons.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/v1/tags")
public class TagController {

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    // http://localhost:6301/v1/tags
    @GetMapping("")
    public R<List<Serializable>> getTagList() {
        // 通过RedisTemplate获取List类型数据的操作器
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        // 在Redis中用于保存标签列表数据的Key
        String tagsKey = "tags";
        // 获取数据
        long start = 0;
        long end = ops.size(tagsKey);
        List<Serializable> list = ops.range(tagsKey, start, end);
        // 返回
        return R.ok(list);
    }

    // http://localhost:6301/v1/tags/1
    @GetMapping("/{id}")
    public Serializable getTagInfo(@PathVariable Integer id){
        ValueOperations<String,Serializable> ops = redisTemplate.opsForValue();
        String key = "tag"+id;
        Serializable value = ops.get(key);
        return value;
    }

}