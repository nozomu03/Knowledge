package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.Service.PostService;
import kr.hs.dgsw.web01blog.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService ps;

    @GetMapping("/post")
    public List<Post> GetAll(){
        return this.ps.Get();
    }

    @PostMapping("/post")
    public boolean Add(@RequestBody Post comment){
        return this.ps.Add(comment);
    }

    @PutMapping("/post/{id}")
    public  boolean Modify(@PathVariable Long id, @RequestBody Post post){
        return this.ps.Modify(id, post.getContent(), post.getTitle());
    }

    @DeleteMapping("/post")
    public boolean Delete(@RequestBody Post comment){
        return this.ps.Delete(comment);
    }
}
