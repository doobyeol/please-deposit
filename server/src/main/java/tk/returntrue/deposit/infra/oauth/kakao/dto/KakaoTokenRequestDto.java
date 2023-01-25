package tk.returntrue.deposit.infra.oauth.kakao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoTokenRequestDto {
    private String grant_type;
    private String client_id;
    private String redirect_uri;
    private String code;
}
