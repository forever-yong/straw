package cn.tedu.straw.redis.hitsquestion.service.impl;


import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.redis.hitsquestion.mapper.QuestionMapper;
import cn.tedu.straw.redis.hitsquestion.service.IQuestionService;
import cn.tedu.straw.redis.hitsquestion.vo.QuestionSimpleListItemVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public List<QuestionSimpleListItemVO> getMostHitsList() {
        return  questionMapper.findMostHitsList();
    }


}
