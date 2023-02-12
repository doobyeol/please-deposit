package tk.returntrue.deposit.application.api.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tk.returntrue.deposit.application.annotations.ApiController;
import tk.returntrue.deposit.domain.user.UserService;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.domain.user.dto.UserDto;

import java.util.List;

@ApiController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/user/loginType/{loginType}")
    public List<UserDto> getUser(@PathVariable LoginType loginType) {
        return userService.findUserList(loginType);
    }

    @GetMapping("/user/{userSeq}")
    public UserDto getUser(@PathVariable Long userSeq) {
        return userService.findUser(userSeq);
    }
}