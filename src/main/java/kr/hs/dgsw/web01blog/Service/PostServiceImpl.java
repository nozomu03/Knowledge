package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository pr;

    @PostConstruct
    private void Init() {
        Post comment = new Post("nozomu03", "니체1", "괴물과 싸우는 사람은 자신이 이 과정에서 괴물이 되지 않도록 조심해야 한다. 만일 네가 괴물의 심연을 오랫동안 들여다보고 있으면, 심연도 네 안으로 들어가 너를 들여다본다.");
        Post comment1 = new Post("nozomu03", "니체2", "모든 신은 죽었다. 이제 위버멘쉬가 등장하기를 우리는 바란다.");
        Post comment2 = new Post("nozomu03", "니체3", "종교는 천민의 사건이다.");
        this.Add(comment);
        this.Add(comment1);
        this.Add(comment2);
    }
    @Override
    public List<Post> Get() {
        return this.pr.findAll();
    }

    @Override
    public Post Select(Long postId){
        return this.pr.findById(postId).get();
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
    public boolean Delete(Long id) {
        Optional<Post> found = this.pr.findById(id);
        if(found.isPresent()) {
            this.pr.deleteById(id);
            return true;
        }
        return false;
    }
}
