package tk.returntrue.deposit.domain.group;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.returntrue.deposit.application.common.dto.LoginUserDto;
import tk.returntrue.deposit.domain.group.constants.UserGroupRole;
import tk.returntrue.deposit.domain.group.constants.UserGroupStatus;
import tk.returntrue.deposit.domain.group.dto.GroupDto;
import tk.returntrue.deposit.infra.group.entity.Group;
import tk.returntrue.deposit.infra.group.entity.UserGroup;
import tk.returntrue.deposit.infra.group.repository.GroupRepository;
import tk.returntrue.deposit.infra.group.repository.UserGroupRepository;
import tk.returntrue.deposit.infra.user.entity.User;
import tk.returntrue.deposit.infra.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;

    public List<GroupDto> findUserGroupList(Long userSeq) {
        List<UserGroup> userGroupList = userGroupRepository.findByUserSeq(userSeq);

        List<GroupDto> groups = userGroupList.stream()
                .map(userGroup -> {
                    // group name
                    Group group = groupRepository.findById(userGroup.getGroupId()).get();

                    // member count, owner
                    List<UserGroup> members = userGroupRepository.findByGroupIdAndStatus(userGroup.getGroupId(), UserGroupStatus.ACCEPTED);
                    UserGroup owner = members.stream()
                            .filter(member -> member.getRole().equals(UserGroupRole.OWNER))
                            .findFirst()
                            .get();

                    // owner nickname
                    User ownerUser = userRepository.findById(owner.getUserSeq()).get();

                    return GroupDto.from(group, ownerUser, members);
                })
                .collect(Collectors.toList());

        return groups;
    }

    @Transactional
    public GroupDto createGroup(GroupDto groupDto, LoginUserDto loginUserDto) {
        Group group = Group.from(groupDto, loginUserDto.getUserSeq());
        Group savedGroup = groupRepository.save(group);
        UserGroup userGroup = UserGroup.from(savedGroup.getGroupId(), loginUserDto.getUserSeq());
        userGroupRepository.save(userGroup);

        return GroupDto.from(savedGroup);
    }
}
