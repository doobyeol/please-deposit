package tk.returntrue.deposit.application.web.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public LoginWebController(UserService userService, @Qualifier("KakaoOAuthService") OAuthService kakaoOAuthService) {
        this.userService = userService;
        this.kakaoOAuthService = kakaoOAuthService;
    }

    @GetMapping("/login/kakao/callback")
    public void kakaoLoginCallback(HttpServletResponse response, @RequestParam String code) throws IOException {
        log.info("kakaoLoginCallback");
        log.info("code : {}", code);

        // 토큰발급 API -> 토큰 발급
        // 토큰으로 사용자 정보 조회
        AuthDto authDto = kakaoOAuthService.authenticate(code);
        log.info("kakaoLoginCallback - authDto : {}", authDto);

        // ID, 닉네임으로 우리 DB에 insert
        UserDto userDto = userService.createUserWithOAuth(authDto, LoginType.KAKAO);
        log.info("kakaoLoginCallback - userDto : {}", userDto);
        // actoken , retoken 발급 (UserDto) -> ac만 쿠키셋팅
        // redirect
        response.sendRedirect("http://127.0.0.1:5173/main");
    }


}
