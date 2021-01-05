package cn.tedu.straw.api.user.service.impl;


import cn.tedu.straw.api.user.dto.StudentRegisterDTO;

import cn.tedu.straw.api.user.mapper.ClassInfoMapper;
import cn.tedu.straw.api.user.mapper.UserMapper;
import cn.tedu.straw.api.user.mapper.UserRoleMapper;

import cn.tedu.straw.api.user.service.IUserService;
import cn.tedu.straw.api.user.util.PasswordUtils;
import cn.tedu.straw.commons.ex.ClassDisabledException;
import cn.tedu.straw.commons.ex.InsertException;
import cn.tedu.straw.commons.ex.InviteCodeException;
import cn.tedu.straw.commons.ex.PhoneDuplicateException;
import cn.tedu.straw.commons.model.ClassInfo;
import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.model.UserRole;
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
    ClassInfoMapper classInfoMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public void studentRegister(StudentRegisterDTO studentRegisterDTO) {
        // 【1】 检查邀请码是否正确
        // 基于参数调用classInfoMapper.findByInviteCode()方法执行查询
        String inviteCode = studentRegisterDTO.getInviteCode();
        ClassInfo classInfo = classInfoMapper.findByInviteCode(inviteCode);
        // 判断查询结果是否为null
        if (classInfo == null) {
            // 是：根据邀请码找不到数据，表示邀请码是错误的，则抛出InviteCodeException
            throw new InviteCodeException("注册失败！邀请码错误！");
        }

        // 【2】 检查邀请码对应的班级是否启用
        // 检查以上查询结果（班级信息）中的isEnabled是否为0
        if (classInfo.getIsEnabled() == 0) {
            // 是：表示班级已被禁用，则抛出ClassDisabledException
            throw new ClassDisabledException("注册失败！班级已经被禁用！");
        }

        // 【3】 检查手机号码是否被占用
        // 基于参数调用userMapper.findByPhone()方法执行查询
        String phone = studentRegisterDTO.getPhone();
        User result = userMapper.findByPhone(phone);
        // 判断查询结果是否不为null
        if (result != null) {
            // 是：找到了用户数据，表示手机号码已经被占用，则抛出PhoneDuplicateException
            throw new PhoneDuplicateException("注册失败！手机号码已经被占用！");
        }

        // 【4】 执行插入用户数据
        // 创建当前时间对象now < LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        // 创建User对象
        User user = new User();
        // 补全User对象的属性：username < 参数
        user.setUsername(studentRegisterDTO.getUsername());
        // 补全User对象的属性：nickName < 参数
        user.setNickName(studentRegisterDTO.getNickName());
        // 补全User对象的属性：password < 参数
        String rawPassword = studentRegisterDTO.getPassword();
        String encodePassword = PasswordUtils.encode(rawPassword);
        user.setPassword(encodePassword);
        // 补全User对象的属性：gender < 参数
        user.setGender(studentRegisterDTO.getGender());
        // 补全User对象的属性：dayOfBirth < 参数
        user.setDayOfBirth(studentRegisterDTO.getDayOfBirth());
        // 补全User对象的属性：phone < 参数
        user.setPhone(phone);
        // 补全User对象的属性：classId < 从此前查询得到的ClassInfo对象中获取
        user.setClassId(classInfo.getId());
        // 补全User对象的属性：isEnabled < 1
        user.setIsEnabled(1);
        // 补全User对象的属性：isLocked < 0
        user.setIsLocked(0);
        // 补全User对象的属性：accountType < 0
        user.setAccountType(0);
        // 补全User对象的属性：gmtCreate < now
        user.setGmtCreate(now);
        // 补全User对象的属性：gmtModified < now
        user.setGmtModified(now);
        // 调用userMapper.insert()方法执行插入用户数据，并获取返回的“受影响行数”
        int rows = userMapper.insert(user);
        // 判断返回的“受影响行数”是否不为1
        if (rows != 1) {
            // 是：插入用户数据失败，则抛出InsertException
            throw new InsertException("注册失败！插入数据时出现错误，请稍后再次尝试！");
        }

        //向user_role表中插入数据,用于分配新注册的用户的角色
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(2);//1.管理员,2学生,3老师
        userRole.setGmtCreate(now);
        user.setGmtModified(now);
        rows = userRoleMapper.insert(userRole);
        if (rows != 1) {
            throw new InsertException("注册失败！设置学生角色时出现错误，请稍后再次尝试！");
        }
    }

}
