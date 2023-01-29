package tk.returntrue.deposit.domain.security.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import tk.returntrue.deposit.domain.user.dto.UserDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestContextHelper {

    public static UserDto getLoginUserInfo() {
        return (UserDto) RequestContextHolder.getRequestAttributes().getAttribute("loginUserInfo", RequestAttributes.SCOPE_SESSION);
    }

    public static void setLoginUserInfo(UserDto userDto) {
        RequestContextHolder.getRequestAttributes().setAttribute("loginUserInfo", userDto, RequestAttributes.SCOPE_SESSION);
    }

}
