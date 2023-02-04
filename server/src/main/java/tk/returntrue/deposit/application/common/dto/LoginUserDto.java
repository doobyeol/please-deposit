package tk.returntrue.deposit.application.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.returntrue.deposit.domain.user.constants.LoginType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
    private Long userSeq;
    private String userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;
    private LoginType loginType;
}
