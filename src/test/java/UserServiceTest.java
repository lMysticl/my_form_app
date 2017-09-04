import com.logiston.entity.User;
import com.logiston.repository.UserRepository;
import com.logiston.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


/**
 * This test only demo
 *
 * @author Pavel Putrenkov
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static final String USER_EMAIL = "test@email.com";
    private static final Long USER_ID = 1L;

    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private User user;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        userServiceImpl = new UserServiceImpl();
        userServiceImpl.setUserRepository(userRepository);
    }

    @Test
    public void shouldReturnUser_whenGetUserByIdIsCalled() throws Exception {

        user.setId(USER_ID);

        when(userRepository.findOne(USER_ID)).thenReturn(user);
        User actualUser = userServiceImpl.getUserById(USER_ID);

        assertThat(actualUser, is(equalTo(user)));
    }

    @Test
    public void shouldReturnUser_whenGetUserByEmailCalled() throws Exception {
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(user);
        User actualUser = userServiceImpl.findUserByEmail(USER_EMAIL);

        assertThat(actualUser, is(equalTo(user)));
    }

    @Test
    public void shouldCallSaveMethodOfProductRepository_whenSaveUserIsCalled() throws Exception {

        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userServiceImpl.saveUser(user);

        assertThat(savedUser, is(equalTo(user)));
    }

    @Test
    public void shouldCallDeleteMethodOfUserRepository_whenDeleteUserIsCalled() throws Exception {


        doNothing().when(userRepository).delete(USER_ID);
        UserRepository my = Mockito.mock(UserRepository.class);
        userServiceImpl.delete(USER_ID);

        verify(userRepository, times(1)).delete(USER_ID);

    }


}