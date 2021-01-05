package cn.tedu.straw.portal.security;


import cn.tedu.straw.portal.service.IUserService;
import cn.tedu.straw.portal.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    //    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        {
//            // 根据用户名得到用户的详情
//            // 假设允许登录的用户名是admin，密码原文是123456
//            if (username == null || !"admin".equals(username)) {
//                return null;
//            }
//            UserDetails userDetails = User.builder()
//                    .username("admin")
//                    .password("{bcrypt}$2a$10$q6sTLG13Ufje/FZDdSzniuYK/eqptQOSgSCn7/BJoLeQjPry4WSpG")
//                    .accountLocked(false)
//                    .disabled(false)
//                    .authorities("管理员")
//                    .build();
//            return userDetails;
//        }
//    }
    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       if(username==null){
           return null;
       }
        UserLoginVO userLoginVO = userService.getLoginUserInfo(username);
       if(userLoginVO==null){
           return null;
       }
//       String[] authorities = new String[userLoginVO.getPermissions().size()];
//        for (int i = 0; i < authorities.length; i++) {
//            authorities[i]=userLoginVO.getPermissions().get(i).getAuthority();
//        }
//        UserDetails userDetails = User.builder()
//                .username(userLoginVO.getPhone())
//                .password(userLoginVO.getPassword())
//                .accountLocked(userLoginVO.getIsLocked()==1)
//                .disabled(userLoginVO.getIsEnabled()==0)
//                .authorities(authorities)
//                .build();
//        return userDetails;
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (int i = 0; i < userLoginVO.getPermissions().size(); i++) {
            String authority = userLoginVO.getPermissions().get(i).getAuthority();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
            authorities.add(simpleGrantedAuthority);
        }
        LoginUserInfo loginUserInfo = new LoginUserInfo(
                userLoginVO.getPhone(),
                userLoginVO.getPassword(),
                userLoginVO.getIsEnabled()==1,
                true,
                true,
                userLoginVO.getIsLocked()==0,
                authorities
        );
        loginUserInfo.setId(userLoginVO.getId());
        loginUserInfo.setNickName(userLoginVO.getNickName());
        loginUserInfo.setPhone(userLoginVO.getPhone());
        loginUserInfo.setAccountType(userLoginVO.getAccountType());
        return loginUserInfo;
    }


}
