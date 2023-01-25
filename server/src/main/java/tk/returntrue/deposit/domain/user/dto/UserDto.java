package tk.returntrue.deposit.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import tk.returntrue.deposit.domain.oauth.dto.AuthDto;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.infra.user.entity.User;

@Data
@Builder
public class UserDto {
    private Long userSeq;
    private String userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;
    private LoginType loginType;

    public static UserDto from(User user) {
        UserDto dto = UserDto.builder()
                .userSeq(user.getUserSeq())
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .accessToken(user.getAccessToken())
                .refreshToken(user.getRefreshToken())
                .loginType(user.getLoginType())
                .build();
        return  dto;
    }
}
