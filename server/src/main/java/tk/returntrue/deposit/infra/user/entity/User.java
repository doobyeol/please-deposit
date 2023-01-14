package tk.returntrue.deposit.infra.user.entity;

import lombok.Getter;
import lombok.ToString;
import tk.returntrue.deposit.domain.user.constants.LoginType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "User")
@Table(name = "USER_INFO")
@ToString
@Getter
public class User {
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

    @Column
    private LocalDateTime createdAt;

    @Column
    private String createdBy;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private String updatedBy;

}
