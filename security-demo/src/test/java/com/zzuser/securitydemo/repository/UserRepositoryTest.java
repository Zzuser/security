package com.zzuser.securitydemo.repository;

import com.zzuser.securitydemo.SecurityDemoApplicationTests;

import com.zzuser.securitydemo.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRepositoryTest extends SecurityDemoApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void insert() {
        User zls = userRepository.findByUsername("zls");
        System.out.println(zls);
    }
}