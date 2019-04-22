package kr.hs.dgsw.web01blog.Repository;

import kr.hs.dgsw.web01blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount(String account);
}
