package cn.tedu.straw.redis.teacher.service.impl;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.vo.TeacherVO;
import cn.tedu.straw.redis.teacher.mapper.UserMapper;
import cn.tedu.straw.redis.teacher.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<TeacherVO> getTeacherList() {
        return userMapper.findAllTeachers();
    }
}
