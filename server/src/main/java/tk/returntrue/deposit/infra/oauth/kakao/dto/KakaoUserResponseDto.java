package tk.returntrue.deposit.infra.oauth.kakao.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class KakaoUserResponseDto {
    private String id;
    private Map<String, String> properties;
}
