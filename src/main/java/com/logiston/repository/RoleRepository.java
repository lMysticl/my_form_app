package com.logiston.repository;

import com.logiston.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pavel Putrenkov
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}
