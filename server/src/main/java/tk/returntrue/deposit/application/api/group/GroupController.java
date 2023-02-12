package tk.returntrue.deposit.application.api.group;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.returntrue.deposit.application.annotations.ApiController;
import tk.returntrue.deposit.application.common.dto.LoginUserDto;
import tk.returntrue.deposit.domain.group.GroupService;
import tk.returntrue.deposit.domain.group.constants.UserGroupStatus;
import tk.returntrue.deposit.domain.group.dto.GroupDto;

import java.util.List;

@ApiController
@RequiredArgsConstructor
@Slf4j
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/group/user/list")
    public List<GroupDto> getUserGroup(LoginUserDto loginUserDto) {
        return groupService.findUserGroupList(loginUserDto.getUserSeq());
    }

    @PostMapping("/group")
    public GroupDto createGroup(@Validated @RequestBody GroupDto groupDto, LoginUserDto loginUserDto) {
        return groupService.createGroup(groupDto, loginUserDto);
    }

    @PutMapping("/group/{groupId}/status/{status}")
    public void updateGroupStatus(@PathVariable Long groupId, @PathVariable UserGroupStatus status, LoginUserDto loginUserDto) {
        groupService.updateGroupStatus(groupId, status, loginUserDto);
    }
}

