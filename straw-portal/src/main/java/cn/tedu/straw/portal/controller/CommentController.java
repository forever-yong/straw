package cn.tedu.straw.portal.controller;


import cn.tedu.straw.portal.dto.PostCommentDTO;
import cn.tedu.straw.portal.ex.ParamValidationException;
import cn.tedu.straw.portal.security.LoginUserInfo;
import cn.tedu.straw.portal.service.ICommentService;
import cn.tedu.straw.portal.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/portal/comments")
public class CommentController {

    @Autowired
    ICommentService commentService;

    // http://localhost:8080/portal/comments/post?answerId=5&content=Test5
    @RequestMapping("/post")
    public R<Void> post(@Valid PostCommentDTO postCommentDTO,
                        BindingResult bindingResult,
                        @AuthenticationPrincipal LoginUserInfo loginUserInfo) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new ParamValidationException(errorMessage);
        }
        Integer userId = loginUserInfo.getId();
        String userNickName = loginUserInfo.getNickName();
        commentService.post(postCommentDTO, userId, userNickName);
        return R.ok();
    }
}
