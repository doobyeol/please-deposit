package tk.returntrue.deposit.domain.common.utils;

import java.util.UUID;

public class TokenUtility {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static String parseAccessToken(String authorization) {
        return authorization.replace("Bearer ", "");
    }

}
