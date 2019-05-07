package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.Service.PostService;
import kr.hs.dgsw.web01blog.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Collections;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService ps;

    @GetMapping("/post")
    public List<Post> Get(){
        List<Post> temp = this.ps.Get();
        Collections.reverse(temp);
        return temp;
    }

    @GetMapping("/post/{id}")
    public Post Select(@PathVariable Long id){
        return this.ps.Select(id);
    }

    @PostMapping("/post")
    public boolean Add(@RequestBody Post comment){
        boolean how = this.ps.Add(comment);
        if(how)
            return true;
        else
            return false;
    }

    @PutMapping("/post/{id}")
    public boolean Modify(@PathVariable Long id, @RequestBody Post post){
        boolean how = this.ps.Modify(id, post.getContent(), post.getTitle());
        if(how)
            return true;
        else
            return false;
    }

    @DeleteMapping("/post/{id}")
    public boolean Delete(@PathVariable Long id){
        boolean how = this.ps.Delete(id);
        if(how)
            return true;
        else
            return false;
    }
}
