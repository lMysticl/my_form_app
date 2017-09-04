package com.logiston.services.impl;

import com.logiston.entity.Theme;
import com.logiston.repository.ThemeRepository;
import com.logiston.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Pavel Putrenkov
 */
@Component
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public void setThemeRepository(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public List<Theme> listAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public Theme getThemeById(Long id) {
        return themeRepository.findOne(id);
    }

    @Override
    public Theme saveTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public void delete(Long id) {
        themeRepository.delete(id);
    }
}
