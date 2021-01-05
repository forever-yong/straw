package cn.tedu.straw.gateway.service.impl;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.gateway.mapper.UserMapper;
import cn.tedu.straw.gateway.service.IUserService;
import cn.tedu.straw.gateway.vo.UserLoginVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


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
    public UserLoginVO getLoginUserInfo(String phone) {
        UserLoginVO userLoginVO = userMapper.findLoginVOByPhone(phone);
        return userLoginVO;
    }
}
