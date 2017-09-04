package com.logiston.repository;

import com.logiston.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pavel Putrenkov
 */
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}