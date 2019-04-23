package kr.hs.dgsw.web01blog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;

    private Long postId;

    public Attachment(String filePath, Long postId) {
        this.filePath = filePath;
        this.postId = postId;
    }

    public Attachment(Long postId) {
        this.postId = postId;
    }
}
