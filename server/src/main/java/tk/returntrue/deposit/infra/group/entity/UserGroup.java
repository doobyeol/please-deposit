package tk.returntrue.deposit.infra.group.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tk.returntrue.deposit.domain.common.entity.BaseEntity;
import tk.returntrue.deposit.domain.group.constants.UserGroupRole;
import tk.returntrue.deposit.domain.group.constants.UserGroupStatus;

import javax.persistence.*;

@Entity
@Table(name = "user_group")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userGroupId;

    @Column
    private Long groupId;

    @Column
    private Long userSeq;

    @Column
    @Enumerated(EnumType.STRING)
    private UserGroupRole role;

    @Column
    @Enumerated(EnumType.STRING)
    private UserGroupStatus status;


    public static UserGroup from(Long groupId, Long userSeq) {
        UserGroup userGroup = UserGroup.builder()
                .groupId(groupId)
                .userSeq(userSeq)
                .role(UserGroupRole.OWNER)
                .status(UserGroupStatus.ACCEPTED)
                .build();

        userGroup.setCreatedBy(String.valueOf(userSeq));
        userGroup.setUpdatedBy(String.valueOf(userSeq));
        return userGroup;
    }

}
