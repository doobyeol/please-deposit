package tk.returntrue.deposit.infra.oauth.kakao.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoTokenResponseDto;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoTokenRequestDto;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoUserResponseDto;

@Component
@RequiredArgsConstructor
public class KakaoApi {
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoApiClient kakaoApiClient;

    public KakaoTokenResponseDto getToken(String code) {
        KakaoTokenRequestDto codeDto = KakaoTokenRequestDto.builder()
                .grant_type("authorization_code")
                .client_id("f59502021c10423854ce6680682e1ebf")
                .redirect_uri("http://localhost:8080/login/kakao/callback")
                .code(code)
                .build();

        return kakaoAuthClient.getToken(codeDto);
    }

    public KakaoUserResponseDto getUser(String accessToken) {
        return kakaoApiClient.getUser("Bearer " + accessToken);
    }
}
