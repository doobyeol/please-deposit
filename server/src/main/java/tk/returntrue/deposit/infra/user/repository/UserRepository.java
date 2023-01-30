package tk.returntrue.deposit.infra.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.returntrue.deposit.domain.user.constants.LoginType;
import tk.returntrue.deposit.infra.user.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLoginType(LoginType loginType);
    User findByUserIdAndLoginType(String userId, LoginType loginType);
    User findByAccessToken(String accessToken);
    User findByRefreshToken(String refreshToken);
}
