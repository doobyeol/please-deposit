package tk.returntrue.deposit.domain.group.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.returntrue.deposit.infra.group.entity.Group;
import tk.returntrue.deposit.infra.group.entity.UserGroup;
import tk.returntrue.deposit.infra.user.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private Long groupId;
    @NotNull(message = "groupName is required")
    @NotEmpty(message = "groupName is required")
    private String groupName;
    private String groupOwner;
    private int memberCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    public static GroupDto from(Group groupEntity) {
        GroupDto groupDto = GroupDto.builder()
                .groupId(groupEntity.getGroupId())
                .groupName(groupEntity.getGroupName())
                .createdAt(groupEntity.getCreatedAt())
                .build();

        return groupDto;
    }

    public static GroupDto from(Group group, User ownerUser, List<UserGroup> members) {
        return GroupDto.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .groupOwner(ownerUser.getNickname())
                .memberCount(members.size())
                .createdAt(group.getCreatedAt())
                .build();
    }
}
