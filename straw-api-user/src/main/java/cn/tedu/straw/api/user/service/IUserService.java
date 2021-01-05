package cn.tedu.straw.api.user.service;


import cn.tedu.straw.api.user.dto.StudentRegisterDTO;
import cn.tedu.straw.commons.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

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



}
