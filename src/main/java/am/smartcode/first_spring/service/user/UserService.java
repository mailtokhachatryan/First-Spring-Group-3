package am.smartcode.first_spring.service.user;

import am.smartcode.first_spring.model.dto.user.CreateUserDto;
import am.smartcode.first_spring.model.dto.user.UpdateUserDto;
import am.smartcode.first_spring.model.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    void login(String email, String password);

    void register(CreateUserDto createUserDto);

    void verify(String email, String code);

    UserEntity create(UserEntity user);

    UserEntity update(UpdateUserDto updateUserDto, int id);

    UserEntity getById(int id);

    List<UserEntity> getAll();

    void delete(int id);

    UserEntity getByEmail(String email);
}
