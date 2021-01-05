package cn.tedu.straw.portal.service.impl;

import cn.tedu.straw.portal.dto.PostQuestionDTO;
import cn.tedu.straw.portal.ex.InsertException;
import cn.tedu.straw.portal.ex.QuestionNotFoundException;
import cn.tedu.straw.portal.mapper.QuestionMapper;
import cn.tedu.straw.portal.mapper.QuestionTagMapper;
import cn.tedu.straw.portal.mapper.UserQuestionMapper;
import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.model.QuestionTag;
import cn.tedu.straw.portal.model.User;
import cn.tedu.straw.portal.model.UserQuestion;
import cn.tedu.straw.portal.service.IQuestionService;
import cn.tedu.straw.portal.vo.QuestionSimpleListItemVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    @Autowired
    QuestionTagMapper questionTagMapper;
    @Autowired
    UserQuestionMapper userQuestionMapper;
    @Transactional
    public void post(PostQuestionDTO postQuestionDTO,Integer userId,String userNickName) {
        // 创建当前时间对象now
        LocalDateTime now = LocalDateTime.now();

        // 创建Question类的对象
        Question question = new Question();
        // 向Question对象中封装数据：title < 参数
        question.setTitle(postQuestionDTO.getTitle());
        // 向Question对象中封装数据：content < 参数
        question.setContent(postQuestionDTO.getContent());
        // 向Question对象中封装数据：userId < 参数
        question.setUserId(userId);
        // 向Question对象中封装数据：userNickName < 参数
        question.setUserNickName(userNickName);
        // 向Question对象中封装数据：status < 0
        question.setStatus(0);
        // 向Question对象中封装数据：hits < 0
        question.setHits(0);
        // 向Question对象中封装数据：tagIds < 参数
        String tagIdString = Arrays.toString(postQuestionDTO.getTagIds());
        question.setTagIds(tagIdString);
        // 向Question对象中封装数据：gmtCreate < now
        question.setGmtCreate(now);
        // 向Question对象中封装数据：gmtModified < now
        question.setGmtModified(now);
        // 调用questionMapper.insert(question)方法向数据库中存入“问题”的数据，并获取返回结果
        int rows = questionMapper.insert(question);
        // 判断返回的“受影响行数”是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("发布问题失败！服务器忙，请稍后再次尝试！");
        }

        // 遍历参数tagIds
        Integer[] tagIds = postQuestionDTO.getTagIds();
        for (int i = 0; i < tagIds.length; i++) {
            // 创建QuestionTag对象
            QuestionTag questionTag = new QuestionTag();
            // 向QuestionTag对象中封装数据：tagId < 被遍历到的tagId
            questionTag.setTagId(tagIds[i]);
            // 向QuestionTag对象中封装数据：questionId < question.getId()
            questionTag.setQuestionId(question.getId());
            // 向QuestionTag对象中封装数据：gmtCreate < now
            questionTag.setGmtCreate(now);
            // 向QuestionTag对象中封装数据：gmtModified < now
            questionTag.setGmtModified(now);
            // 调用questionTagMapper.insert(questionTag)方法向数据库中存入“问题”与“标签”的对应关系的数据，并获取返回结果
            rows = questionTagMapper.insert(questionTag);
            // 判断返回的“受影响行数”是否不为1
            if (rows != 1) {
                // 是：抛出InsertException
                throw new InsertException("发布问题失败！服务器忙，请稍后再次尝试！");
            }
        }

        // 遍历参数teacherIds
        Integer[] teacherIds = postQuestionDTO.getTeacherIds();
        for (int i = 0; i < teacherIds.length; i++) {
            // 创建UserQuestion对象
            UserQuestion userQuestion = new UserQuestion();
            // 向UserQuestion对象中封装数据：userId < 被遍历到的teacherId
            userQuestion.setUserId(teacherIds[i]);
            // 向UserQuestion对象中封装数据：questionId < question.getId()
            userQuestion.setQuestionId(question.getId());
            // 向UserQuestion对象中封装数据：gmtCreate < now
            userQuestion.setGmtCreate(now);
            // 向UserQuestion对象中封装数据：gmtModified < now
            userQuestion.setGmtModified(now);
            // 调用userQuestionMapper.insert(userQuestion)方法向数据库中存入“问题”与“回答问题的老师”的对应关系的数据，并获取返回结果
            rows = userQuestionMapper.insert(userQuestion);
            // 判断返回的“受影响行数”是否不为1
            if (rows != 1) {
                // 是：抛出InsertException
                throw new InsertException("发布问题失败！服务器忙，请稍后再次尝试！");
            }
        }


    }

    @Override
    public List<QuestionSimpleListItemVO> getMostHitsList() {
        return  questionMapper.findMostHitsList();
    }

    @Value("${project.my-questions.page-size}")
    Integer pageSize;
    @Override
    public PageInfo<Question> getMyQuestions(Integer userId, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Question> questions = questionMapper.findByUserId(userId);
        return new PageInfo<>(questions);
    }

    @Override
    public Question getQuestionDetails(Integer id) {
        Question question = questionMapper.findById(id);
        if (question == null) {
            throw new QuestionNotFoundException("获取问题详情失败！尝试访问的数据不存在！");
        }
        return question;
    }
}
