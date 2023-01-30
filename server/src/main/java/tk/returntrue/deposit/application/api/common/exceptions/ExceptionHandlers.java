package tk.returntrue.deposit.application.api.common.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tk.returntrue.deposit.domain.common.exceptions.AuthException;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDto handleException(Exception e) {
        log.error("", e);
        return ErrorResponseDto.builder().message(e.getMessage()).build();
    }

    @ExceptionHandler(value = { NoSuchElementException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDto handleNotFoundException(NoSuchElementException e) {
        log.error("", e);
        return ErrorResponseDto.builder().message(e.getMessage()).build();
    }

    @ExceptionHandler(value = { AuthenticationException.class, AuthException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseDto handleAuthenticationException(Exception e) {
        log.error("", e);
        return ErrorResponseDto.builder().message(e.getMessage()).build();
    }

}
