package tk.returntrue.deposit.application.api.group;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import tk.returntrue.deposit.application.annotations.ApiController;
import tk.returntrue.deposit.domain.group.GroupService;
import tk.returntrue.deposit.domain.group.dto.GroupDto;

import java.util.List;

@ApiController
@RequiredArgsConstructor
@Slf4j
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/group/list")
    public List<GroupDto> getGroup() {
        return groupService.findGroupList();
    }
}

