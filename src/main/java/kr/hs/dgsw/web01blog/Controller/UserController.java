package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Service.UserService;
import kr.hs.dgsw.web01blog.Service.UserServiceImpl;
import kr.hs.dgsw.web01blog.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService us;

    @GetMapping("/user")
    public List<User> list(){
        return this.us.Get();
    }

    @PostMapping("/user")
    public boolean Add(@RequestBody User u){
        return this.us.Add(u);
    }

    @PutMapping("/user/{id}")
    public boolean Modify(@PathVariable Long id, @RequestBody User u){
        return this.us.Modify(id, u);
    }

    @DeleteMapping("/user")
    public boolean Delete(@RequestBody String account){
        return this.us.Delete(account);
    }
}
