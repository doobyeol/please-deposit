package tk.returntrue.deposit.domain.group;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.returntrue.deposit.domain.group.dto.GroupDto;
import tk.returntrue.deposit.infra.group.entity.GroupEntity;
import tk.returntrue.deposit.infra.group.repository.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public List<GroupDto> findGroupList() {
        List<GroupEntity> groupList = groupRepository.findAll();
        return groupList.stream()
                .map(entity -> GroupDto.from(entity))
                .collect(Collectors.toList());
    }
}
