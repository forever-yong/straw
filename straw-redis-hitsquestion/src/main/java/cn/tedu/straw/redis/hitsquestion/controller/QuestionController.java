package cn.tedu.straw.redis.hitsquestion.controller;

import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.redis.hitsquestion.service.IQuestionService;
import cn.tedu.straw.redis.hitsquestion.vo.QuestionSimpleListItemVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/v1/questions")
public class QuestionController {
    @Autowired
    IQuestionService questionService;

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

//
//    // http://localhost:6303/v1/questions/most-hits
//    @GetMapping("/most-hits")
//    public R<List<QuestionSimpleListItemVO>> getMostHitsList() {
//        return R.ok(questionService.getMostHitsList());
//    }

    //http://localhost/redis-hitsquestion/v1/questions
    // http://localhost:6303/v1/questions
    @GetMapping("")
    public R<List<Serializable>> getMostHitsList() {
        // 通过RedisTemplate获取List类型数据的操作器
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        // 在Redis中用于保存标签列表数据的Key
        String hitsquestionKey = "hitsQuestion";
        // 获取数据
        long start = 0;
        long end = ops.size(hitsquestionKey);
        List<Serializable> list = ops.range(hitsquestionKey, start, end);
        // 返回
        return R.ok(list);
    }

}
