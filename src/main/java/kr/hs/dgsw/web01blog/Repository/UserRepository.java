package kr.hs.dgsw.web01blog.Repository;

import kr.hs.dgsw.web01blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);
}
