package com.zzuser.securitydemo.controller;

import com.zzuser.securitydemo.vo.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzhaoctr
 */
@RestController
public class LoginController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/user/test")
    public JsonResult user() {
        return JsonResult.ok("/user,具有ROLE_USER可以访问");
    }

    @RequestMapping("/admin")
    public JsonResult admin() {
        return JsonResult.ok("/admin,具有ROLE_ADMIN可以访问");
    }



}
