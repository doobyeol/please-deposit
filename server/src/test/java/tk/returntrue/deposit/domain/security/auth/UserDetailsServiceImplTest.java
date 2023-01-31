package tk.returntrue.deposit.domain.security.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import tk.returntrue.deposit.domain.common.exceptions.AuthException;
import tk.returntrue.deposit.domain.user.UserService;
import tk.returntrue.deposit.domain.user.dto.UserDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    UserService userService;

    UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void init() {
        userDetailsService = new UserDetailsServiceImpl(userService);
    }

    @Test
    public void loadUserByUsername_성공() {
        // given
        String accessToken = "1234";
        UserDto userDto = UserDto.builder()
                .userSeq(1L)
                .nickname("doobyeol")
                .accessToken("1234")
                .accessTokenExpireAt(LocalDateTime.now().plusDays(1))
                .build();

        when(userService.findByAccessToken(accessToken)).thenReturn(userDto);

        // when
        UserDetails userDetails = userDetailsService.loadUserByUsername(accessToken);

        // then
        assertEquals(String.valueOf(userDto.getUserSeq()), userDetails.getUsername());
    }

    @Test
    public void loadUserByUsername_유저없음() {
        // given
        String accessToken = "1234";

        when(userService.findByAccessToken(accessToken)).thenReturn(null);

        // then
        Throwable exception = assertThrows(AuthException.class, () -> {
            // when
            userDetailsService.loadUserByUsername(accessToken);
        });

        assertEquals("No user found with access token. UserDto is null", exception.getMessage());
    }

    @Test
    public void loadUserByUsername_토큰만료() {
        // given
        String accessToken = "1234";
        UserDto userDto = UserDto.builder()
                .userSeq(1L)
                .nickname("doobyeol")
                .accessToken("1234")
                .accessTokenExpireAt(LocalDateTime.now().minusDays(1))
                .build();

        when(userService.findByAccessToken(accessToken)).thenReturn(userDto);

        // then
        Throwable exception = assertThrows(AuthException.class, () -> {
            // when
            userDetailsService.loadUserByUsername(accessToken);
        });

        assertEquals("Expired access token", exception.getMessage());
    }
}
