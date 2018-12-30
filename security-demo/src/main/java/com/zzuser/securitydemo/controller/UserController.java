package com.zzuser.securitydemo.controller;

import com.zzuser.securitydemo.domain.User;
import com.zzuser.securitydemo.vo.JsonResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zzhaoctr
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserDetailsService userDetailsService;

    /**
     * 获取登录用户信息
     *
     * @param authentication
     * @return
     */
    @GetMapping
    public JsonResult findUserByName(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return JsonResult.ok(principal);
    }

}
