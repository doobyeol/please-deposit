package tk.returntrue.deposit.infra.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.returntrue.deposit.infra.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
