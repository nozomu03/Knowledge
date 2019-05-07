package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.domain.User;

import java.util.List;

public interface UserService {
    List<User> Get();
    User GetOne(String account);
    boolean Add(User u);
    boolean Modify(Long id, User u);
    boolean Delete(Long id);
}
