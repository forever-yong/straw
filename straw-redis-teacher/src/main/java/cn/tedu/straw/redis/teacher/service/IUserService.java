package cn.tedu.straw.redis.teacher.service;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.vo.TeacherVO;
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

    List<TeacherVO> getTeacherList();

}
