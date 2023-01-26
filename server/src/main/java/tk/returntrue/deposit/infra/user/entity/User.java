package tk.returntrue.deposit.infra.user.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tk.returntrue.deposit.domain.common.entity.BaseEntity;
import tk.returntrue.deposit.domain.oauth.dto.AuthDto;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.domain.user.dto.UserDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_info")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userSeq;

    @Column
    private String userId;

    @Column
    private String nickname;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    @Column
    private LocalDateTime accessTokenExpireAt;

    @Column
    private LocalDateTime refreshTokenExpireAt;

    @Column
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static User from(UserDto userDto) {
        User user = User.builder()
                .userId(userDto.getUserId())
                .nickname(userDto.getNickname())
                .loginType(userDto.getLoginType())
                .build();

        user.setCreatedBy(userDto.getUserId());
        user.setUpdatedBy(userDto.getUserId());

        return user;
    }

    public static User from(AuthDto authDto, LoginType loginType) {
        User user = User.builder()
                .userId(authDto.getId())
                .nickname(authDto.getNickname())
                .loginType(loginType)
                .build();

        user.setCreatedBy(authDto.getId());
        user.setUpdatedBy(authDto.getId());

        return user;
    }

    public static User from(AuthDto authDto, LoginType loginType, String accessToken, String refreshToken) {
        User user = User.builder()
                .userId(authDto.getId())
                .nickname(authDto.getNickname())
                .loginType(loginType)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        user.setCreatedBy(authDto.getId());
        user.setUpdatedBy(authDto.getId());

        return user;
    }
}
