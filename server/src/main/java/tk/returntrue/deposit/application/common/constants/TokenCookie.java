package tk.returntrue.deposit.application.common.constants;

import lombok.Getter;

import javax.servlet.http.Cookie;

@Getter
public enum TokenCookie {
    ACCESS_TOKEN("acstk", 60 * 60 * 24, "/"),
    REFRESH_TOKEN("rftk", 60 * 60 * 24 * 7, "/"),
    ;

    TokenCookie(String key, int maxAge, String path) {
        this.key = key;
        this.maxAge = maxAge;
        this.path = path;
    }

    private String key;
    private int maxAge;
    private String path;

    public Cookie makeCookie(String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath(path);
        return cookie;
    }

}
