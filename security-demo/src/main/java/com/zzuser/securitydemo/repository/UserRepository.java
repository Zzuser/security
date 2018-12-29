package com.zzuser.securitydemo.repository;


import com.zzuser.securitydemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zzhaoctr
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * 根据id查找用户
     *
     * @param id ID
     * @return
     */
    User findById(Integer id);

    /**
     * 根据username查找角色
     *
     * @param username 用户名
     * @return
     */
    User findByUsername(String username);
}
