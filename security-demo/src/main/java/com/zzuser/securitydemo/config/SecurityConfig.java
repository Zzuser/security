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
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .logoutUrl("/logout")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/").access("hasRole('ROLE_USER')")
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .cors()//新加入
                .and()
                .csrf().disable();
    }

    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            System.out.println(authentication);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonResult ok = JsonResult.ok("登录成功");
            out.write(objectMapper.writeValueAsString(ok));
            out.flush();
            out.close();
        };
    }

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

    private LogoutSuccessHandler logoutSuccessHandler(){
        return (request, response, authentication) -> {
            System.out.println(authentication);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
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
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        String encode = e.encode("123456");
        System.out.println(encode);
    }
}