package cn.tedu.straw.api.question.service.impl;

import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.api.question.mapper.QuestionMapper;
import cn.tedu.straw.api.question.mapper.QuestionTagMapper;
import cn.tedu.straw.api.question.mapper.UserQuestionMapper;
import cn.tedu.straw.api.question.message.PostQuestionMessage;
import cn.tedu.straw.api.question.service.IQuestionService;
import cn.tedu.straw.api.question.vo.QuestionListItemVO;
import cn.tedu.straw.commons.ex.InsertException;
import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.vo.TagVO;
import cn.tedu.straw.portal.model.QuestionTag;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionTagMapper questionTagMapper;
    @Autowired
    UserQuestionMapper userQuestionMapper;
    @Transactional
    public void post(PostQuestionDTO postQuestionDTO, Integer userId, String userNickName) {
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
    }

    @Value("${project.my-questions.page-size}")
    Integer pageSize;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public PageInfo<QuestionListItemVO> getMyQuestions(Integer userId, Integer pageNum) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 通过持久层查询数据库，得到“问题”列表，该结果中各“问题”并不包含标签列表的数据
        List<QuestionListItemVO> questions = questionMapper.findByUserId(userId);
        // 遍历得到的“问题”列表，准备为每一个问题补全标签数据
        for (QuestionListItemVO question : questions) {
            // 从“问题”中取出标签ID的字符串，例如 [8, 10, 15]
            String tagIdsString = question.getTagIds();
            log.debug("当前问题的标签ID列表字符串：{}", tagIdsString);
            // 将以上得到的 [8, 10, 15] 这样的结果转换为String[]
            String tagIds = tagIdsString.substring(1, tagIdsString.length() - 1);
            String[] tagIdsArray = tagIds.split(", ");
            log.debug("将标签ID列表字符串拆为数组：");
            for (String s : tagIdsArray) {
                log.debug("Tag ID={}", s);
            }
            // 准备一个List集合，用于存放当前“问题”的各标签
            List<TagVO> tags = new ArrayList<>();
            // 遍历标签ID列表数组，逐一获取对应的标签数据
            for (String tagId : tagIdsArray) {
                // 设置远程调用的URL，即通过哪个URL可以得到所需的数据
                // 注意，以下URL中：
                // -- 主机名和端口号部分需要使用对应的微服务在Eureka Server中注册的名称
                // -- 参数部分使用 {编号} 这样的占位符来表示，编号是从0开始顺序编号的
                String url = "http://redis-tag/v1/tags/{0}";
                // 通过RestTemplate实现远程调用
                // 注意：如果调用后返回的结果是集合类型，以下方法的第2个参数需要写为数组类型，例如String[].class
                TagVO tag = restTemplate.getForObject(url, TagVO.class, tagId);
                // 将得到的标签数据放到List集合中
                tags.add(tag);
            }
            // 设置“问题”的标签列表属性
            question.setTags(tags);
        }
        // 返回分页后的列表对象
        return new PageInfo<>(questions);
  }

  @Autowired
  Gson gson;
    @KafkaListener(topics = "POST_QUESTION")
    public void receivePostQuestion(ConsumerRecord<String,String> record){
        //接收消息,得到消息中的数据
        String data = record.value();
        //将数据还原回对象
        PostQuestionMessage message = gson.fromJson(data,PostQuestionMessage.class);
        //调用原有的业务方法来处理"发布问题
        post(message.getPostQuestionDTO(), message.getUserId(), message.getUserNickName());
    }

}

