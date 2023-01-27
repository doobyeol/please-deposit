package tk.returntrue.deposit.application.web.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.returntrue.deposit.domain.oauth.OAuthService;
import tk.returntrue.deposit.domain.oauth.dto.AuthDto;
import tk.returntrue.deposit.domain.user.UserService;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.domain.user.dto.UserDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class LoginWebController {

    private final UserService userService;
    private final OAuthService kakaoOAuthService;

    @Value("${oauth.login-redirect-uri}")
    private String loginRedirectUri;

    public LoginWebController(UserService userService, @Qualifier("KakaoOAuthService") OAuthService kakaoOAuthService) {
        this.userService = userService;
        this.kakaoOAuthService = kakaoOAuthService;
    }

    @GetMapping("/login/kakao/callback")
    public void kakaoLoginCallback(HttpServletResponse response, @RequestParam String code) throws IOException {
        AuthDto authDto = kakaoOAuthService.authenticate(code);
        userService.createUserWithOAuth(authDto, LoginType.KAKAO);
        response.sendRedirect(loginRedirectUri);
    }


}
