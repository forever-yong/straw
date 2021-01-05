package cn.tedu.straw.redis.teacher.mapper;


import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.vo.TeacherVO;
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

    List<TeacherVO> findAllTeachers();
}
