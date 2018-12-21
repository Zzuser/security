package com.zzuser.securitydemo.controller;

import com.zzuser.securitydemo.domain.User;
import com.zzuser.securitydemo.vo.JsonResult;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{username}")
    public JsonResult findUserByName(@PathVariable String username){
        return JsonResult.ok(userDetailsService.loadUserByUsername(username));

    }

}
