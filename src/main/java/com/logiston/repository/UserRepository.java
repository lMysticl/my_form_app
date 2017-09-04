package com.logiston.repository;

import com.logiston.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pavel Putrenkov
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
