package cn.tedu.straw.redis.hitsquestion.service;


import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.redis.hitsquestion.vo.QuestionSimpleListItemVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
public interface IQuestionService extends IService<Question> {


    List<QuestionSimpleListItemVO> getMostHitsList();


}
