package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.vo.QuestionSimpleListItemVO;
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

    Question findById(Integer id);

    List<Question> findByUserId(Integer userId);

    List<QuestionSimpleListItemVO> findMostHitsList();
}
