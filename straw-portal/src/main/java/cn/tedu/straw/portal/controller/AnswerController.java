package cn.tedu.straw.portal.controller;


import cn.tedu.straw.portal.dto.PostAnswerDTO;
import cn.tedu.straw.portal.ex.ParamValidationException;
import cn.tedu.straw.portal.security.LoginUserInfo;
import cn.tedu.straw.portal.service.IAnswerService;
import cn.tedu.straw.portal.util.R;
import cn.tedu.straw.portal.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/portal/answers")
public class AnswerController {
    @Autowired
    IAnswerService answerService;

    // http://localhost:8080/portal/answers/post?questionId=5&content=Test5
    @RequestMapping("/post")
    public R<Void> post(@Valid PostAnswerDTO postAnswerDTO,
                        BindingResult bindingResult,
                        @AuthenticationPrincipal LoginUserInfo loginUserInfo) {
        if (bindingResult.hasErrors()) {
            String errorMessage =  bindingResult.getFieldError().getDefaultMessage();
            throw new ParamValidationException(errorMessage);
        }
        Integer userId = loginUserInfo.getId();
        String userNickName = loginUserInfo.getNickName();
        answerService.post(postAnswerDTO, userId, userNickName);
        return R.ok();
    }


    // http://localhost:8080/portal/answers?questionId=5
    @GetMapping("")
    public R<List<AnswerVO>> getAnswerList(Integer questionId) {
        if (questionId == null || questionId < 1) {
            throw new ParamValidationException("获取答案列表失败！缺少必要的参数！");
        }
        return R.ok(answerService.getAnswerList(questionId));
    }

}
