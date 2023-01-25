package tk.returntrue.deposit.domain.oauth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
    private String id;
    private String nickname;
}
