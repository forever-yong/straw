package cn.tedu.straw.api.question.service;


import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.api.question.vo.QuestionListItemVO;
import cn.tedu.straw.commons.model.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
public interface IQuestionService extends IService<Question> {

    void post(PostQuestionDTO postQuestionDTO , Integer userId, String userName);

    PageInfo<QuestionListItemVO> getMyQuestions(Integer userId, Integer pageNum);

}
