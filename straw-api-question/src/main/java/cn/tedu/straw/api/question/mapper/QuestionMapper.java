package cn.tedu.straw.api.question.mapper;

import cn.tedu.straw.api.question.vo.QuestionListItemVO;
import cn.tedu.straw.commons.model.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    List<QuestionListItemVO> findByUserId(Integer userId);
}
