package tk.returntrue.deposit.application.api.login;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tk.returntrue.deposit.application.annotations.ApiController;
import tk.returntrue.deposit.application.common.constants.TokenCookie;
import tk.returntrue.deposit.domain.user.UserService;
import tk.returntrue.deposit.domain.user.dto.TokenDto;
import tk.returntrue.deposit.domain.user.dto.UserDto;

import javax.servlet.http.HttpServletResponse;

@ApiController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login/token/access")
    public UserDto login(@RequestBody TokenDto tokenDto, HttpServletResponse response) {
        UserDto userDto = userService.loginByAccessToken(tokenDto.getAccessToken());

        response.addCookie(TokenCookie.ACCESS_TOKEN.makeCookie(userDto.getAccessToken()));
        response.addCookie(TokenCookie.REFRESH_TOKEN.makeCookie(userDto.getRefreshToken()));

        return userDto;
    }

    @PostMapping("/login/token/refresh")
    public UserDto refresh(@RequestBody TokenDto tokenDto, HttpServletResponse response) {
        UserDto userDto = userService.loginByRefreshToken(tokenDto.getRefreshToken());

        response.addCookie(TokenCookie.ACCESS_TOKEN.makeCookie(userDto.getAccessToken()));
        response.addCookie(TokenCookie.REFRESH_TOKEN.makeCookie(userDto.getRefreshToken()));

        return userDto;
    }

}
