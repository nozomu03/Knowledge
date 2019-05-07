package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Service.UserService;
import kr.hs.dgsw.web01blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService us;

    @GetMapping("/user")
    public List<User> Get(){
        return this.us.Get();
    }

    @GetMapping("/user/{account}")
    public User GetOne(@PathVariable String account){
        return this.us.GetOne(account);
    }

    @PostMapping("/user")
    public boolean Add(@RequestBody User u){
        boolean how = this.us.Add(u);
        if(how)
            return true;
        else
            return false;
    }

    @PutMapping("/user/{id}")
    public boolean Modify(@PathVariable Long id, @RequestBody User u){
        boolean how =  this.us.Modify(id, u);
        if(how)
            return true;
        else
            return false;
    }

    @DeleteMapping("/user")
    public boolean Delete(@RequestBody Long id){
        boolean how = this.us.Delete(id);
        if(how)
            return true;
        else
            return false;

    }
}
