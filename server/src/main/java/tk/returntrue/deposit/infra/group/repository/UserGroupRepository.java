package tk.returntrue.deposit.infra.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.returntrue.deposit.domain.group.constants.UserGroupStatus;
import tk.returntrue.deposit.infra.group.entity.UserGroup;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findByUserSeq(Long userSeq);
    List<UserGroup> findByGroupIdAndStatusNot(Long groupId, UserGroupStatus status);
    UserGroup findByGroupIdAndUserSeq(Long groupId, Long userSeq);
}
