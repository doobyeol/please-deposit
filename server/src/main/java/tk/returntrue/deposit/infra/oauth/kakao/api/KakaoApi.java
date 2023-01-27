package tk.returntrue.deposit.infra.oauth.kakao.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoTokenResponseDto;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoTokenRequestDto;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoUserResponseDto;

@Component
@RequiredArgsConstructor
public class KakaoApi {
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoApiClient kakaoApiClient;

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    public KakaoTokenResponseDto getToken(String code) {
        KakaoTokenRequestDto codeDto = KakaoTokenRequestDto.builder()
                .grant_type("authorization_code")
                .client_id(clientId)
                .redirect_uri(redirectUri)
                .code(code)
                .build();

        return kakaoAuthClient.getToken(codeDto);
    }

    public KakaoUserResponseDto getUser(String accessToken) {
        return kakaoApiClient.getUser("Bearer " + accessToken);
    }
}
