package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
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

    @GetMapping("/test")
    public String Test(){
        return "Hello";
    }

    @GetMapping("/user")
    public ResponseFormat Get(){
        List<User> temp = this.us.Get();
        return new ResponseFormat(ResponseType.USER_LIST, "LIST",  Integer.parseInt(""+temp.size()));
    }

    @PostMapping("/user")
    public ResponseFormat Add(@RequestBody User u){
        boolean how = this.us.Add(u);
        if(how)
            return new ResponseFormat(ResponseType.USER_ADD, "Added", u.getId());
        else
            return new ResponseFormat(ResponseType.FAIL, "Can't add");
    }

    @PutMapping("/user/{id}")
    public ResponseFormat Modify(@PathVariable Long id, @RequestBody User u){
        boolean how =  this.us.Modify(id, u);
        if(how)
            return new ResponseFormat(ResponseType.USER_UPDATE, "Updated", u.getId());
        else
            return new ResponseFormat(ResponseType.FAIL, "Can't update");
    }

    @DeleteMapping("/user")
    public ResponseFormat Delete(@RequestBody Long id){
        boolean how = this.us.Delete(id);
        if(how)
            return new ResponseFormat(ResponseType.USER_UPDATE, "delete", id);
        else
            return new ResponseFormat(ResponseType.FAIL, "Can't delete");

    }
}
