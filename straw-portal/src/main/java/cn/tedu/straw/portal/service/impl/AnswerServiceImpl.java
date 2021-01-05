package cn.tedu.straw.portal.service.impl;

import cn.tedu.straw.portal.dto.PostAnswerDTO;
import cn.tedu.straw.portal.ex.InsertException;
import cn.tedu.straw.portal.ex.QuestionNotFoundException;
import cn.tedu.straw.portal.mapper.AnswerMapper;
import cn.tedu.straw.portal.mapper.QuestionMapper;
import cn.tedu.straw.portal.model.Answer;
import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.service.IAnswerService;
import cn.tedu.straw.portal.vo.AnswerVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    AnswerMapper answerMapper;

    @Override
    public List<AnswerVO> getAnswerList(Integer questionId) {
        return answerMapper.findByQuestionId(questionId);
    }

    @Override
    public void post(PostAnswerDTO postAnswerDTO, Integer userId, String userNickName) {
        // 从参数postAnswerDTO中获取questionId
        Integer questionId = postAnswerDTO.getQuestionId();
        // 调用questionMapper.findById()查询“问题”
        Question question = questionMapper.findById(questionId);
        // 判断查询结果是否为null
        if (question == null) {
            // 是：抛出QuestionNotFoundException
            throw new QuestionNotFoundException("发表答案失败！问题数据不存在，可能已被删除！");
        }

        // 创建当前时间对象now
        LocalDateTime now = LocalDateTime.now();
        // 创建Answer对象
        Answer answer = new Answer();
        // 向Answer对象中补全数据：content < 参数postAnswerDTO
        answer.setContent(postAnswerDTO.getContent());
        // 向Answer对象中补全数据：userId < 参数userId
        answer.setUserId(userId);
        // 向Answer对象中补全数据：userNickName < 参数userNickName
        answer.setUserNickName(userNickName);
        // 向Answer对象中补全数据：questionId < questionId
        answer.setQuestionId(questionId);
        // 向Answer对象中补全数据：isAccepted < 0
        answer.setIsAccepted(0);
        // 向Answer对象中补全数据：gmtCreate < now
        answer.setGmtCreate(now);
        // 向Answer对象中补全数据：gmtModified < now
        answer.setGmtModified(now);
        // 调用answerMapper.insert()插入“答案”数据，并获取返回结果
        int rows = answerMapper.insert(answer);
        // 判断返回结果是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发表答案失败！服务器忙，请稍后再次尝试！");
        }
    }

}
