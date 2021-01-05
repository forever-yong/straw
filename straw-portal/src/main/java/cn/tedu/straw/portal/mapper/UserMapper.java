package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.model.User;
import cn.tedu.straw.portal.vo.TeacherVO;
import cn.tedu.straw.portal.vo.UserLoginVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User findByPhone(String phone);

    UserLoginVO findLoginVOByPhone(String phone);

    List<TeacherVO> findAllTeachers();
}
