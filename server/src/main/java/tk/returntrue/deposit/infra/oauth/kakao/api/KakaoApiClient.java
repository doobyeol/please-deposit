package tk.returntrue.deposit.infra.oauth.kakao.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import tk.returntrue.deposit.infra.oauth.kakao.dto.KakaoUserResponseDto;

@FeignClient(name = "kakao-api", url = "https://kapi.kakao.com")
public interface KakaoApiClient {

    @GetMapping(value = "/v2/user/me", produces = MediaType.APPLICATION_JSON_VALUE)
    KakaoUserResponseDto getUser(@RequestHeader(name = "Authorization") String authorization);
}
