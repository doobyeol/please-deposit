package tk.returntrue.deposit.application.api.common.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.returntrue.deposit.domain.common.dto.BaseDto;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ErrorResponseDto extends BaseDto {
    private String message;
}
