package tk.returntrue.deposit.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.returntrue.deposit.domain.common.exceptions.AuthException;
import tk.returntrue.deposit.domain.common.utils.TokenUtility;
import tk.returntrue.deposit.domain.oauth.dto.AuthDto;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.domain.user.dto.TokenDto;
import tk.returntrue.deposit.domain.user.dto.UserDto;
import tk.returntrue.deposit.infra.user.entity.User;
import tk.returntrue.deposit.infra.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> findUserList(LoginType loginType) {
        List<User> userList = userRepository.findByLoginType(loginType);
        return userList.stream()
                .map(entity -> UserDto.from(entity))
                .collect(Collectors.toList());
    }

    public UserDto findUser(Long userSeq) {
        Optional<User> userOpt = userRepository.findById(userSeq);
        User user = userOpt.orElseThrow(() -> new NoSuchElementException("No value present"));
        return UserDto.from(user);
    }

    public UserDto createUser(UserDto userDto) {
        User userEntity = User.from(userDto);
        User savedUser = userRepository.save(userEntity);
        return UserDto.from(savedUser);
    }

    public UserDto findByAccessToken(String accessToken) {
        User userEntity = userRepository.findByAccessToken(accessToken);
        return UserDto.from(userEntity);
    }

    @Transactional
    public UserDto createUserWithOAuth(AuthDto authDto, LoginType loginType) {
        User userEntity = userRepository.findByUserIdAndLoginType(authDto.getId(), loginType);
        String accessToken = TokenUtility.generateToken();
        String refreshToken = TokenUtility.generateToken();

        if (Objects.isNull(userEntity)) {
            userEntity = User.from(authDto, loginType, accessToken, refreshToken);
        }
        userEntity.updateToken(accessToken, refreshToken);
        User savedUser = userRepository.save(userEntity);
        return UserDto.from(savedUser);
    }

    public UserDto updateUser(UserDto userDto) {
        Optional<User> userOpt = userRepository.findById(userDto.getUserSeq());
        if (userOpt.isEmpty()) {
            return null;
        }
        User userEntity = userOpt.get();
        userEntity.updateNickname(userDto.getNickname());

        User savedUser = userRepository.save(userEntity);
        return UserDto.from(savedUser);
    }

    public void deleteUser(Long userSeq) {
        userRepository.deleteById(userSeq);
    }

    @Transactional
    public UserDto loginByRefreshToken(String refreshToken) {
       User userEntity = userRepository.findByRefreshToken(refreshToken);
       if (Objects.isNull(userEntity)) {
           throw new AuthException("No user found with refresh token. userEntity is null");
       }

       LocalDateTime refreshTokenExpireAt = userEntity.getRefreshTokenExpireAt();
       if (LocalDateTime.now().isAfter(refreshTokenExpireAt)) {
           throw new AuthException("Expired refresh token");
       }

       userEntity.updateToken(TokenUtility.generateToken(), TokenUtility.generateToken());

       return UserDto.from(userEntity);
    }


    @Transactional
    public UserDto loginByAccessToken(String accessToken) {
       User userEntity = userRepository.findByAccessToken(accessToken);
       if (Objects.isNull(userEntity)) {
           throw new AuthException("No user found with access token. userEntity is null");
       }

       LocalDateTime accessTokenExpireAt = userEntity.getAccessTokenExpireAt();
       if (LocalDateTime.now().isAfter(accessTokenExpireAt)) {
           throw new AuthException("Expired access token");
       }

       userEntity.updateToken(TokenUtility.generateToken(), TokenUtility.generateToken());

       return UserDto.from(userEntity);
    }
}
