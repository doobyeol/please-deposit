package tk.returntrue.deposit.domain.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tk.returntrue.deposit.domain.common.exceptions.AuthException;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenUtility {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static String parseAccessToken(String authorization) {
        if (!authorization.startsWith("Bearer ")) {
            throw new AuthException("Invalid authorization type");
        }
        return authorization.replace("Bearer ", "");
    }

}
