package cn.tedu.straw.portal.service.impl;

import cn.tedu.straw.portal.dto.PostCommentDTO;
import cn.tedu.straw.portal.ex.AnswerNotFoundException;
import cn.tedu.straw.portal.ex.InsertException;
import cn.tedu.straw.portal.mapper.AnswerMapper;
import cn.tedu.straw.portal.mapper.CommentMapper;
import cn.tedu.straw.portal.model.Answer;
import cn.tedu.straw.portal.model.Comment;
import cn.tedu.straw.portal.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void post(PostCommentDTO postCommentDTO, Integer userId, String userNickName) {

        // 从参数postCommentDTO中获取answerId
        Integer answerId = postCommentDTO.getAnswerId();
        // 调用answerMapper.findById()查询“答案”
        Answer answer = answerMapper.findById(answerId);
        // 判断查询结果是否为null
        if (answer == null) {
            // 是：抛出AnswerNotFoundException
            throw new AnswerNotFoundException("发表评论失败！答案数据不存在！");
        }

        // 创建当前时间对象now
        LocalDateTime now = LocalDateTime.now();
        // 创建Comment对象
        Comment comment = new Comment();
        // 向Comment对象中补全数据：content < 参数postCommentDTO
        comment.setContent(postCommentDTO.getContent());
        // 向Comment对象中补全数据：userId < 参数userId
        comment.setUserId(userId);
        // 向Comment对象中补全数据：userNickName < 参数userNickName
        comment.setUserNickName(userNickName);
        // 向Comment对象中补全数据：answerId < answerId
        comment.setAnswerId(answerId);
        // 向Comment对象中补全数据：gmtCreate < now
        comment.setGmtCreate(now);
        // 向Comment对象中补全数据：gmtModified < now
        comment.setGmtModified(now);
        // 调用commentMapper.insert()插入“评论”数据，并获取返回结果
        int rows = commentMapper.insert(comment);
        // 判断返回结果是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发表评论失败！服务器忙，请稍后再次尝试！");
        }

    }
}
