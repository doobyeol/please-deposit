package tk.returntrue.deposit.domain.group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tk.returntrue.deposit.application.common.dto.LoginUserDto;
import tk.returntrue.deposit.domain.common.exceptions.BadRequestException;
import tk.returntrue.deposit.domain.group.constants.UserGroupRole;
import tk.returntrue.deposit.domain.group.constants.UserGroupStatus;
import tk.returntrue.deposit.infra.group.entity.UserGroup;
import tk.returntrue.deposit.infra.group.repository.GroupRepository;
import tk.returntrue.deposit.infra.group.repository.UserGroupRepository;
import tk.returntrue.deposit.infra.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    GroupRepository groupRepository;
    @Mock
    UserGroupRepository userGroupRepository;
    @Mock
    UserRepository userRepository;

    GroupService groupService;

    @BeforeEach
    public void init() {
        groupService = new GroupService(groupRepository, userGroupRepository, userRepository);
    };

    @Test
    public void updateGroupStatus_성공() {
        // given
        Long groupId = 1L;
        UserGroupStatus status = UserGroupStatus.ACCEPTED;
        LoginUserDto loginUserDto = LoginUserDto.builder()
                .userSeq(10L)
                .userId("doobyeol")
                .accessToken("123")
                .build();

        UserGroup userGroup = UserGroup.builder()
                .userGroupId(1L)
                .groupId(groupId)
                .userSeq(loginUserDto.getUserSeq())
                .role(UserGroupRole.MEMBER)
                .status(UserGroupStatus.WAITING)
                .build();
        when(userGroupRepository.findByGroupIdAndUserSeq(groupId, loginUserDto.getUserSeq())).thenReturn(userGroup);

        // when
        groupService.updateGroupStatus(groupId, status, loginUserDto);

        // then
        assertEquals(userGroup.getStatus(), status);
    }
    
    
    @Test
    @DisplayName("WAITING이아닐때")
    public void updateGroupStatus_실패() {
        // given
        Long groupId = 1L;
        UserGroupStatus status = UserGroupStatus.ACCEPTED;
        LoginUserDto loginUserDto = LoginUserDto.builder()
                .userSeq(10L)
                .userId("doobyeol")
                .accessToken("123")
                .build();

        UserGroup userGroup = UserGroup.builder()
                .userGroupId(1L)
                .groupId(groupId)
                .userSeq(loginUserDto.getUserSeq())
                .role(UserGroupRole.MEMBER)
                .status(UserGroupStatus.REJECTED)
                .build();
        when(userGroupRepository.findByGroupIdAndUserSeq(groupId, loginUserDto.getUserSeq())).thenReturn(userGroup);

        // then
        assertThrows(BadRequestException.class, () -> {
            // when
            groupService.updateGroupStatus(groupId, status, loginUserDto);
        });
    }
}
