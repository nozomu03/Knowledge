package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.domain.Post;

import java.util.List;

public interface PostService {
    List<Post> Get();
    Post Select(Long userId);
    boolean Add(Post comment);
    boolean Modify(Long id, String content, String title);
    boolean Delete(Long id);
}
