package tk.returntrue.deposit.infra.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.returntrue.deposit.infra.group.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
