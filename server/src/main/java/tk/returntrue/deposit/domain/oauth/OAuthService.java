package tk.returntrue.deposit.domain.oauth;

import tk.returntrue.deposit.domain.oauth.dto.AuthDto;

public interface OAuthService {
    AuthDto authenticate(String code);
}
