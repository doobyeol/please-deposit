package tk.returntrue.deposit.infra.group.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tk.returntrue.deposit.domain.common.entity.BaseEntity;
import tk.returntrue.deposit.domain.group.dto.GroupDto;

import javax.persistence.*;

@Entity
@Table(name = "group_info")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Group extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long groupId;

    @Column
    private String groupName;


    public static Group from(GroupDto groupDto, Long userSeq) {
        Group group = Group.builder()
                .groupName(groupDto.getGroupName())
                .build();

        group.setCreatedBy(String.valueOf(userSeq));
        group.setUpdatedBy(String.valueOf(userSeq));

        return group;
    }
}
