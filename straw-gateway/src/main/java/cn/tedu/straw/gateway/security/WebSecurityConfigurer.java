package cn.tedu.straw.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);


}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录页面
        String loginPageUrl="/login.html";
        //处理登录的URL,即登录表单提交到哪里去
        String loginProcessingUrl = "/login";
        //不需要登录即可访问的URL
        String[] urls = {
          loginPageUrl,
          loginProcessingUrl,
          "/favicon.ico",
          "/browser_components/**",
          "/css/**",
          "/img/**",
          "/js/**",
          "/npm/**",
          "/register.html",
          "/api-user/v1/users/reg/student"
        };
        // 授权访问
        http.authorizeRequests()
                .antMatchers(urls).permitAll()
                .antMatchers("/test/user/add").hasAnyAuthority("创建用户","管理员")
                .antMatchers("/test/user/delete").hasAnyAuthority("删除用户","管理员")
                .antMatchers("/test/user/update").hasAnyAuthority("修改用户","管理员")
                .antMatchers("/test/user/list").hasAnyAuthority("查看用户","管理员")
                .anyRequest().authenticated();

        http.formLogin()
            .loginPage(loginPageUrl)
            .loginProcessingUrl(loginProcessingUrl);


        http.csrf().disable();
    }


}
