package tk.returntrue.deposit.domain.group.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.returntrue.deposit.infra.group.entity.GroupEntity;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private Long groupId;
    private String groupName;
    private String groupOwner;
    private int memberCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    public static GroupDto from(GroupEntity groupEntity) {
        GroupDto groupDto = GroupDto.builder()
                .groupId(groupEntity.getGroupId())
                .groupName(groupEntity.getGroupName())
                .groupOwner(groupEntity.getCreatedBy())
                .createdAt(groupEntity.getCreatedAt())
                .build();

        return groupDto;
    }
}
