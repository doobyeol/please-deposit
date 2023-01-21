package tk.returntrue.deposit.domain.user.dto;

import lombok.Data;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.infra.user.entity.User;

@Data
public class UserDto {
    private Long userSeq;
    private String userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;
    private LoginType loginType;

    public static UserDto from(User user) {
        UserDto dto = new UserDto();
        dto.setUserSeq(user.getUserSeq());
        dto.setUserId(user.getUserId());
        dto.setNickname(user.getNickname());
        dto.setAccessToken(user.getAccessToken());
        dto.setRefreshToken(user.getRefreshToken());
        dto.setLoginType(user.getLoginType());
        return  dto;
    }
}
