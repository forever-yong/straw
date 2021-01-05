package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.dto.StudentRegisterDTO;
import cn.tedu.straw.portal.model.User;
import cn.tedu.straw.portal.vo.TeacherVO;
import cn.tedu.straw.portal.vo.UserLoginVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
public interface IUserService extends IService<User> {

    void studentRegister(StudentRegisterDTO studentRegisterDTO);

    UserLoginVO getLoginUserInfo(String phone);

    List<TeacherVO> getTeacherList();

}
