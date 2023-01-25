package tk.returntrue.deposit.infra.oauth.kakao.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoTokenResponseDto;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoTokenRequestDto;

@FeignClient(name = "kakao-auth", url = "https://kauth.kakao.com")
public interface KakaoAuthClient {

    @PostMapping(value = "/oauth/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoTokenResponseDto getToken(@RequestBody KakaoTokenRequestDto codeDto);
}
