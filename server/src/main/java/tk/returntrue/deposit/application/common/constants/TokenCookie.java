package tk.returntrue.deposit.application.common.constants;

import lombok.Getter;

import javax.servlet.http.Cookie;

@Getter
public enum TokenCookie {
    ACCESS_TOKEN("acstk", 60 * 60 * 24,  "/", false, false),
    REFRESH_TOKEN("rftk", 60 * 60 * 24 * 7, "/", false, false),
    ;

    TokenCookie(String key, int maxAge, String path, boolean secure, boolean httpOnly) {
        this.key = key;
        this.maxAge = maxAge;
        this.path = path;
        this.secure = secure;
        this.httpOnly = httpOnly;
    }

    private String key;
    private int maxAge;
    private String path;
    private boolean secure;
    private boolean httpOnly;

    public Cookie makeCookie(String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(secure);
        cookie.setHttpOnly(httpOnly);
        cookie.setPath(path);
        return cookie;
    }

}
