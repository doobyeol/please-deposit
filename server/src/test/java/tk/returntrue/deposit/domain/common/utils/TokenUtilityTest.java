package tk.returntrue.deposit.domain.common.utils;

import org.junit.jupiter.api.Test;
import tk.returntrue.deposit.domain.common.exceptions.AuthException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenUtilityTest {

    @Test
    public void parseAccessToken_정상() {
        // given
        String authorization = "Bearer 1234";

        // when
        String result = TokenUtility.parseAccessToken(authorization);

        // then
        assertEquals("1234", result);
    }

    @Test
    public void parseAccessToken_비정상() {
        // given
        String authorization1 = null;
        String authorization2 = "1234";

        // then1
        assertThrows(AuthException.class, () -> {
            // when
            TokenUtility.parseAccessToken(authorization1);
        });

        // then2
        assertThrows(AuthException.class, () -> {
            // when
            TokenUtility.parseAccessToken(authorization2);
        });
    }
}
