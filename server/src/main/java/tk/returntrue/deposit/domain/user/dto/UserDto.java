package tk.returntrue.deposit.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.infra.user.entity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userSeq;
    private String userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;
    private LoginType loginType;

    public static UserDto from(User user) {
        return  UserDto.builder()
                    .userSeq(user.getUserSeq())
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .accessToken(user.getAccessToken())
                    .refreshToken(user.getRefreshToken())
                    .loginType(user.getLoginType())
                    .build();
    }
}
