package tk.returntrue.deposit.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.returntrue.deposit.domain.oauth.dto.AuthDto;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.domain.user.dto.UserDto;
import tk.returntrue.deposit.infra.user.entity.User;
import tk.returntrue.deposit.infra.user.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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

    public UserDto createUserWithOAuth(AuthDto authDto, LoginType loginType) {
        // TODO : 중복체크
        // 유저가 존재하면 조회한 유저로 토큰만 업데이트
        // 유저가 존재하지 않는다면 토큰과 함께 insert
        // TODO : 토큰발급
        // 토큰이 이미 있다면 새 토큰을 업데이트
        User user = User.from(authDto, loginType);
        User saveUser = userRepository.save(user);
        return UserDto.from(saveUser);
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
}
