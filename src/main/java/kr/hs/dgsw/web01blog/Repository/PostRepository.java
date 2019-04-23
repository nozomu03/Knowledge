package kr.hs.dgsw.web01blog.Repository;

import kr.hs.dgsw.web01blog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findTopByUserNameOrderByIdDesc(Long userId);
}
