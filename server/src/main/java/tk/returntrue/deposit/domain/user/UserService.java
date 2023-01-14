package tk.returntrue.deposit.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.returntrue.deposit.domain.user.dto.UserDto;
import tk.returntrue.deposit.infra.user.entity.User;
import tk.returntrue.deposit.infra.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto findUser(Long userSeq) {
        Optional<User> userOpt = userRepository.findById(userSeq);
        User user = userOpt.orElse(null);
        return UserDto.from(user);
    }
}
