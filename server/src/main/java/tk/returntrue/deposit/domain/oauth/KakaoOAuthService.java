package tk.returntrue.deposit.domain.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.returntrue.deposit.domain.oauth.dto.AuthDto;
import tk.returntrue.deposit.infra.oauth.kakao.api.KakaoApi;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoTokenResponseDto;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoUserResponseDto;

@Service("KakaoOAuthService")
@RequiredArgsConstructor
@Slf4j
public class KakaoOAuthService implements OAuthService {

    private final KakaoApi kakaoApi;

    @Override
    public AuthDto authenticate(String code) {
        KakaoTokenResponseDto tokenResponse = kakaoApi.getToken(code);
        KakaoUserResponseDto userResponse = kakaoApi.getUser(tokenResponse.getAccess_token());
        AuthDto authDto = AuthDto.builder()
                .id(userResponse.getId())
                .nickname(userResponse.getProperties().get("nickname"))
                .build();

        log.info("authDto : {}", authDto);
        return authDto;
    }
}
