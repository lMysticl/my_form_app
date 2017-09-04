import com.logiston.entity.Theme;
import com.logiston.repository.ThemeRepository;
import com.logiston.repository.UserRepository;
import com.logiston.services.impl.ThemeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author Pavel Putrenkov
 */
public class ThemeServiceTest {

    private static final Long THEME_ID = 1L;

    private ThemeServiceImpl themeServiceImpl;
    @Mock
    private ThemeRepository themeRepository;
    @Mock
    private Theme theme;
    @Mock
    private List<Theme> themes;


    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        themeServiceImpl = new ThemeServiceImpl();
        themeServiceImpl.setThemeRepository(themeRepository);

    }




    @Test
    public void shouldCallSaveMethodOfProductRepository_whenSaveUserIsCalled() throws Exception {

        when(themeRepository.save(theme)).thenReturn(theme);
        Theme savedUser = themeServiceImpl.saveTheme(theme);

        assertThat(savedUser, is(equalTo(theme)));
    }

    @Test
    public void shouldCallDeleteMethodOfThemeRepository_whenSaveThemeIsCalled() throws Exception {
        doNothing().when(themeRepository).delete(THEME_ID);
        UserRepository my = Mockito.mock(UserRepository.class);
        themeServiceImpl.delete(THEME_ID);

        verify(themeRepository, times(1)).delete(THEME_ID);

    }
}
