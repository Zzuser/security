package com.zzuser.securitydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzuser.securitydemo.vo.JsonResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author zzhaoctr
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserDetailsService userDetailsService;
    @Resource
    ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()//表单验证，为了简单就不搞token验证了
                .loginProcessingUrl("/login")//处理登录请求的api
                //因为表单验证方式默认是跳转页面，而我们前后分离不需要后端处理跳转
                //所以自定义一个登录成功处理器，它只需要告诉我们登录结果就可以了
                .successHandler(successHandler())//登录成功处理器
                .failureHandler(failureHandler())//登录失败处理器
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())//登出处理器
                .logoutUrl("/logout")//登出api
                .permitAll()
                .and()
                .authorizeRequests()//接下来进行鉴权拦截，有相应权限的才可以从接口中得到数据
                .antMatchers("/").access("hasRole('ROLE_USER')")
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .cors()//跨域设置
                .and()
                .csrf()
                .disable(); //关掉csrf防御
    }

    /**
     * 自定义登录成功处理器，成功返回一个带有成功信息的Json数据包装类
     */
    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonResult ok = JsonResult.ok("登录成功");
            out.write(objectMapper.writeValueAsString(ok));
            out.flush();
            out.close();
        };
    }

    /**
     * 自定义登录失败处理器，成功返回一个带有失败信息的Json数据包装类
     */
    private AuthenticationFailureHandler failureHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonResult error = JsonResult.error("账号或密码错误");
            out.write(objectMapper.writeValueAsString(error));
            out.flush();
            out.close();
        };
    }

    /**
     * 自定义登出成功处理器，清除登录信息且成功返回一个带有登出信息的Json数据包装类
     */
    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);//清除登录认证信息
            }
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonResult ok = JsonResult.ok("注销成功");
            out.write(objectMapper.writeValueAsString(ok));
            out.flush();
            out.close();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//向spring注册一个密码加密器
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());//配置密码加密器
    }

    //加密密码生成方法,生成自己想要的密码
    public static void main(String[] args) {
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        String encode = e.encode("123456");
        System.out.println(encode);
    }
}