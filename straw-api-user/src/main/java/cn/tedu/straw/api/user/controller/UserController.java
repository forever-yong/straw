package cn.tedu.straw.api.user.controller;


import cn.tedu.straw.api.user.dto.StudentRegisterDTO;

import cn.tedu.straw.api.user.service.IUserService;
import cn.tedu.straw.commons.ex.ParamValidationException;
import cn.tedu.straw.commons.util.R;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    IUserService userService;

    //http://localhost:8081/v1/users/reg/student?inviteCode=JSD2006-888888&nickName=Jack&phone=18371232717&password=123456
    @RequestMapping("/reg/student")
    public R studentRegister(@Valid StudentRegisterDTO studentRegisterDTO, BindingResult bindingResult){
        R r = new R();
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new ParamValidationException(errorMessage);
        }
            userService.studentRegister(studentRegisterDTO);
            //return new R().setState(1).setMessage("注册成功!");
            return R.ok();
    }

}
