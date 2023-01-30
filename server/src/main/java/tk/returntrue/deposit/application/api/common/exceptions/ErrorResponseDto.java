package tk.returntrue.deposit.application.api.common.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private String message;
}
