package kr.hs.dgsw.web01blog.Repository;

import kr.hs.dgsw.web01blog.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
