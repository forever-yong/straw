package cn.tedu.straw.api.question.controller;



import cn.tedu.straw.api.question.dto.PostQuestionDTO;
import cn.tedu.straw.api.question.message.PostQuestionMessage;
import cn.tedu.straw.api.question.service.IQuestionService;
import cn.tedu.straw.api.question.vo.QuestionListItemVO;
import cn.tedu.straw.commons.ex.ParamValidationException;

import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.util.R;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/v1/questions")
@Slf4j
public class QuestionController {
    @Autowired
    IQuestionService questionService;

    @Autowired(required = false)
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    Gson gson;
    // http://localhost:8082/v1/questions/post?title=Title3&content=Content3&tagIds=5&tagIds=10&tagIds=17&teacherIds=2&teacherIds=5&teacherIds=9
    // http://localhost:/api-question/v1/questions/post?title=Title3&content=Content3&tagIds=5&tagIds=10&tagIds=17&teacherIds=2&teacherIds=5&teacherIds=9
    @RequestMapping("/post")
    public R post(@Valid PostQuestionDTO postQuestionDTO, BindingResult bindingResult, @AuthenticationPrincipal LoginUserInfo loginUserInfo){

        log.debug("QuestionController.post()");
        log.debug("LoginUserInfo >>> {}",loginUserInfo);

        if(bindingResult.hasErrors()){
              String errorMessage = bindingResult.getFieldError().getDefaultMessage();
              throw new ParamValidationException(errorMessage);
          }
          Integer userId = loginUserInfo.getId();
          String userNickName= loginUserInfo.getNickName();
          //questionService.post(postQuestionDTO,userId,userNickName);
        PostQuestionMessage postQuestionMessage = new PostQuestionMessage();
        postQuestionMessage.setPostQuestionDTO(postQuestionDTO);
        postQuestionMessage.setUserId(userId);
        postQuestionMessage.setUserNickName(userNickName);
        String data = gson.toJson(postQuestionMessage);
        kafkaTemplate.send("POST_QUESTION",data);

        return R.ok();
      }

    // http://localhost/api-question/v1/questions/my
    @GetMapping("/my")
    public R<PageInfo<QuestionListItemVO>> getMyQuestions(
            @AuthenticationPrincipal LoginUserInfo loginUserInfo, Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        Integer userId = loginUserInfo.getId();
        PageInfo<QuestionListItemVO> pageInfo = questionService.getMyQuestions(userId, page);
        return R.ok(pageInfo);
    }
}
