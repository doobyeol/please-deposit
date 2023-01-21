package tk.returntrue.deposit.application.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tk.returntrue.deposit.application.annotations.ApiController;
import tk.returntrue.deposit.domain.user.UserService;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.domain.user.dto.UserDto;

import java.util.List;

@ApiController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/by/{loginType}")
    public List<UserDto> getUser(@PathVariable LoginType loginType) {
        return userService.findUserList(loginType);
    }

    @GetMapping("/user/{userSeq}")
    public UserDto getUser(@PathVariable Long userSeq) {
        return userService.findUser(userSeq);
    }

    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/user")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/user/{userSeq}")
    public void deleteUser(@PathVariable Long userSeq) {
        userService.deleteUser(userSeq);
    }
}
