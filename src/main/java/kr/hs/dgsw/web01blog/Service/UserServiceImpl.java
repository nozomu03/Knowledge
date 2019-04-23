package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Repository.UserRepository;
import kr.hs.dgsw.web01blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository ur;

    @Override
    public List<User> Get() {
        return this.ur.findAll();
    }

    @Override
    public boolean Add(User u) {
        List<User> userList = this.ur.findAll();
        for(User temp : userList){
            if(temp.getAccount().equals(u.getAccount()))
                return false;
        }
        this.ur.save(u);
        return true;
    }

    @Override
    public boolean Modify(Long id, User u) {
        Optional<User> temp = this.ur.findById(id);
        if(temp.isPresent()){
            User user = temp.get();
            user=u;
            user.setId(id);
            this.ur.save(user);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean Delete(Long id) {
        if(this.ur.findById(id)!=null){
            this.ur.delete(this.ur.findById(id).get());
            return true;
        }
        else
            return false;
    }
}
