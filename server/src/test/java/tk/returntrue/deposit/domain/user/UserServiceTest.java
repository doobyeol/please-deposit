package tk.returntrue.deposit.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tk.returntrue.deposit.domain.user.dto.UserDto;
import tk.returntrue.deposit.infra.user.entity.User;
import tk.returntrue.deposit.infra.user.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository);
    }

    @Test
    public void findUser_성공() {
        // given
        Long userSeq = 1L;
        User user = User.builder().userId("leaf").build();
        when(userRepository.findById(userSeq)).thenReturn(Optional.of(user));

        // when
        UserDto dto = userService.findUser(userSeq);

        // then
        assertEquals("leaf", dto.getUserId());
    }

    @Test
    public void findUser_유저없음() {
        // given
        Long userSeq = 1L;
        when(userRepository.findById(userSeq)).thenReturn(Optional.ofNullable(null));

        // then
        assertThrows(NoSuchElementException.class, () -> {
            // when
            userService.findUser(userSeq);
        });
    }

}
