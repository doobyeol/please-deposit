package tk.returntrue.deposit.application.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.returntrue.deposit.domain.common.exceptions.AuthException;
import tk.returntrue.deposit.domain.user.UserService;
import tk.returntrue.deposit.domain.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String accessToken) throws UsernameNotFoundException {
        UserDto userDto = userService.findByAccessToken(accessToken);
        if (Objects.isNull(userDto)) {
            throw new AuthException("No user found with access token. UserDto is null");
        }
        LocalDateTime accessTokenExpireAt = userDto.getAccessTokenExpireAt();
        if (LocalDateTime.now().isAfter(accessTokenExpireAt)) {
            throw new AuthException("Expired access token");
        }
        return UserDetailsImpl.build(userDto);
    }
}