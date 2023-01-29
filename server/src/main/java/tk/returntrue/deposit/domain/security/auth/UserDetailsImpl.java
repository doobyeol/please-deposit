package tk.returntrue.deposit.domain.security.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.domain.user.dto.UserDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long userSeq;
    private String userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;
    private LoginType loginType;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long userSeq, String userId, String nickname,
                           String accessToken, String refreshToken, LoginType loginType,
                           Collection<? extends GrantedAuthority> authorities) {
        this.userSeq = userSeq;
        this.userId = userId;
        this.nickname = nickname;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserDto userDto) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserDetailsImpl(userDto.getUserSeq(), userDto.getUserId(),
                userDto.getNickname(), userDto.getAccessToken(), userDto.getRefreshToken(),
                userDto.getLoginType(), authorities);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return accessToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}