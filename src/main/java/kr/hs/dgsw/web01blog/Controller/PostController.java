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
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService ps;

    @GetMapping("/post")
    public ResponseFormat Select(@RequestBody Long id){
        if(this.ps.Select(id) != null)
            return new ResponseFormat(ResponseType.POST_GET, "GET", "SUCCESS");
        else
            return new ResponseFormat(ResponseType.FAIL, "Can't Get");
    }

    @PostMapping("/post")
    public ResponseFormat Add(@RequestBody Post comment){
        boolean how = this.ps.Add(comment);
        if(how)
            return new ResponseFormat(ResponseType.POST_ADD, "ADD", comment.getId());
        else
            return new ResponseFormat(ResponseType.FAIL, "Can't Add");
    }

    @PutMapping("/post/{id}")
    public ResponseFormat Modify(@PathVariable Long id, @RequestBody Post post){
        boolean how = this.ps.Modify(id, post.getContent(), post.getTitle());
        if(how)
            return new ResponseFormat(ResponseType.POST_UPDATE, "UPDATE", post.getId());
        else
            return new ResponseFormat(ResponseType.FAIL, "Can't Update");
    }

    @DeleteMapping("/post")
    public ResponseFormat Delete(@RequestBody Long id){
        boolean how = this.ps.Delete(id);
        if(how)
            return new ResponseFormat(ResponseType.POST_DELETE, "DELETE", id);
        else
            return new ResponseFormat(ResponseType.FAIL, "Can't DELETE");
    }
}
