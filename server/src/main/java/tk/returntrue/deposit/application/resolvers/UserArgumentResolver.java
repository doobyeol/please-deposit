package tk.returntrue.deposit.application.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import tk.returntrue.deposit.application.common.dto.LoginUserDto;
import tk.returntrue.deposit.application.security.auth.UserDetailsImpl;
import tk.returntrue.deposit.domain.common.exceptions.AuthException;

import java.util.Objects;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(LoginUserDto.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication) || !(authentication.isAuthenticated())) {
            throw new AuthException("authentication is null OR not authenticated");
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return LoginUserDto.builder()
                .userSeq(userDetails.getUserSeq())
                .userId(userDetails.getUserId())
                .nickname(userDetails.getNickname())
                .accessToken(userDetails.getAccessToken())
                .refreshToken(userDetails.getRefreshToken())
                .loginType(userDetails.getLoginType())
                .build();
    }
}