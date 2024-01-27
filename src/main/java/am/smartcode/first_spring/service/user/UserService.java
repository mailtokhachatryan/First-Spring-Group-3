package am.smartcode.first_spring.service.user;

import am.smartcode.first_spring.model.User;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    void login(String email, String password);

    void    register(User user);

    @Transactional
    void verify(String email, String code);

    User create(User user);

    User update(User user, int id);

    User getById(int id);

    List<User> getAll();

    void delete(int id);

    User getByEmail(String email);
}
