package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository pr;

    @Override
    public List<Post> Get() {
        return this.pr.findAll();
    }

    @Override
    public boolean Add(Post comment) {
        try {
            this.pr.save(comment);
            return true;
        }catch(Exception e){
            return  false;
        }
    }

    @Override
    public boolean Modify(Long id, String content, String title) {
        Optional<Post> find = this.pr.findById(id);
        if(find.isPresent()){
            Post comment = find.get();
            comment.setContent(content);
            comment.setTitle(title);
            this.pr.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean Delete(Post comment) {
        List<Post> commentList = this.pr.findAll();
        for(Post temp : commentList) {
            if(temp.getTitle().equals(comment.getTitle()) && temp.getUserName().equals(comment.getUserName()) && temp.getContent().equals(comment.getContent())) {
                this.pr.delete(temp);
                return true;
            }
        }
        return false;
    }
}
