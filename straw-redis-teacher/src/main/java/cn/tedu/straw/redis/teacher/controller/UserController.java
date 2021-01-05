package cn.tedu.straw.redis.teacher.controller;

import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.commons.vo.TeacherVO;
import cn.tedu.straw.redis.teacher.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    IUserService userService;

    // http://localhost:6302/v1/users/teachers
    @GetMapping("/teachers")
    public R getTeacherList() {
        List<TeacherVO> teachers = userService.getTeacherList();
        return R.ok(teachers);
    }

}
