package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.dto.PostAnswerDTO;
import cn.tedu.straw.portal.model.Answer;
import cn.tedu.straw.portal.vo.AnswerVO;
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
public interface IAnswerService extends IService<Answer> {

    List<AnswerVO> getAnswerList(Integer questionId);

    void post(PostAnswerDTO postAnswerDTO,Integer userId,String userNickName);

}
