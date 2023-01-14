package tk.returntrue.deposit.application.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tk.returntrue.deposit.application.annotations.ApiController;
import tk.returntrue.deposit.domain.user.UserService;
import tk.returntrue.deposit.domain.user.dto.UserDto;

@ApiController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{userSeq}")
    public UserDto getUser(@PathVariable Long userSeq) {
        return userService.findUser(userSeq);
    }
}
