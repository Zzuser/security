package com.zzuser.securitydemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzuser.securitydemo.domain.Role;
import com.zzuser.securitydemo.domain.User;
import com.zzuser.securitydemo.repository.RoleRepository;
import com.zzuser.securitydemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzhaoctr
 */
@SpringBootApplication
@EnableJpaAuditing
public class SecurityDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * 初始化测试数据
     * @param userRepository
     * @param roleRepository
     * @return
     */
    @Bean
    CommandLineRunner initData(UserRepository userRepository,RoleRepository roleRepository) {
        return args -> {
            Role admin = roleRepository.findByName("ROLE_ADMIN");
            if (ObjectUtils.isEmpty(admin)) {
                admin=new Role();
                admin.setName("ROLE_ADMIN");
                admin.setNote("管理员");
                roleRepository.saveAndFlush(admin);
            }
            Role user = roleRepository.findByName("ROLE_USER");
            if (ObjectUtils.isEmpty(user)) {
                user=new Role();
                user.setName("ROLE_USER");
                user.setNote("用户");
                roleRepository.saveAndFlush(user);
            }
            System.out.println(admin);
            System.out.println(user);

            User zz = userRepository.findByUsername("zz");
            if (ObjectUtils.isEmpty(zz)) {
                zz=new User();
                zz.setUsername("zz");
                zz.setPassword("$2a$10$99UTVBaagS09sKyzYKoqiuW6wSWzoAWu5.PegN6tSY0HxllXt62S.");//123456
                userRepository.save(zz);
                List<Role> zzRoles=new ArrayList<>();
                zzRoles.add(admin);
                zzRoles.add(user);
                zz.setRoleList(zzRoles);
                userRepository.save(zz);
            }
            User zls = userRepository.findByUsername("zls");
            if (ObjectUtils.isEmpty(zls)) {
                zls=new User();
                zls.setUsername("zls");
                zls.setPassword("$2a$10$99UTVBaagS09sKyzYKoqiuW6wSWzoAWu5.PegN6tSY0HxllXt62S.");//123456
                userRepository.save(zls);
                List<Role> zlsRoles=new ArrayList<>();
                zlsRoles.add(user);
                zls.setRoleList(zlsRoles);
                userRepository.save(zls);
            }
        };
    }


}
