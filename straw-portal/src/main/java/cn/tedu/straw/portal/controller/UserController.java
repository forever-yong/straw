package cn.tedu.straw.portal.controller;


import cn.tedu.straw.portal.dto.StudentRegisterDTO;
import cn.tedu.straw.portal.ex.*;
import cn.tedu.straw.portal.service.IUserService;
import cn.tedu.straw.portal.util.R;
import cn.tedu.straw.portal.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RequestMapping("/portal/users")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("/reg/student")
    public R studentRegister(@Valid StudentRegisterDTO studentRegisterDTO,BindingResult bindingResult){
        R r = new R();
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new ParamValidationException(errorMessage);
        }
            userService.studentRegister(studentRegisterDTO);
            //return new R().setState(1).setMessage("注册成功!");
            return R.ok();
    }

    // http://localhost:8080/portal/users/teachers
    @GetMapping("/teachers")
    public R getTeacherList() {
        List<TeacherVO> teachers = userService.getTeacherList();
        return R.ok(teachers);
    }

}
