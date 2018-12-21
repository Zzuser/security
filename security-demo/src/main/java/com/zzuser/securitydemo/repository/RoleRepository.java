package com.zzuser.securitydemo.repository;

import com.zzuser.securitydemo.domain.Role;
import com.zzuser.securitydemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zzhaoctr
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
