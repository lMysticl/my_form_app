package com.logiston.services;


import com.logiston.entity.Theme;
import com.logiston.repository.ThemeRepository;

import java.util.List;

/**
 * @author Pavel Putrenkov
 */
public interface ThemeService {

    void setThemeRepository(ThemeRepository themeRepository);

    List<Theme> listAllThemes();

    Theme getThemeById(Long id);

    Theme saveTheme(Theme theme);

    void delete(Long id);
}
