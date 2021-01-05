package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.dto.PostQuestionDTO;
import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.vo.QuestionSimpleListItemVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

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

    void post(PostQuestionDTO postQuestionDTO ,Integer userId,String userName);

    List<QuestionSimpleListItemVO> getMostHitsList();

    PageInfo<Question> getMyQuestions(Integer userId,Integer pageNum);

    Question getQuestionDetails(Integer id);

}
