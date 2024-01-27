package am.smartcode.first_spring.service.user;


import am.smartcode.first_spring.exception.UserAlreadyExistsException;
import am.smartcode.first_spring.exception.ValidationException;
import am.smartcode.first_spring.exception.VerificationException;
import am.smartcode.first_spring.model.User;
import am.smartcode.first_spring.repository.UserRepository;
import am.smartcode.first_spring.service.email.EmailService;
import am.smartcode.first_spring.util.RandomGenerator;
import am.smartcode.first_spring.util.encoder.MD5Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public void login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (!user.isVerified())
                throw new VerificationException("You must verify your account");
            if (!user.getPassword().equals(MD5Encoder.encode(password)))
                throw new ValidationException("Email or Password not valid");
        } else
            throw new ValidationException("Email or Password not valid");
    }

    @Override
    @Transactional
    public void register(User user) {
        User byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail != null)
            throw new UserAlreadyExistsException();
        String code = RandomGenerator.generateNumericString(6);
        user.setVerified(false);
        user.setCode(code);
        user.setPassword(MD5Encoder.encode(user.getPassword()));

        emailService.sendEmail(user.getEmail(), "Verification", "Your code is " + code);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void verify(String email, String code) {
        User byEmail = userRepository.findByEmail(email);
        if (!code.equals(byEmail.getCode())) {
            throw new VerificationException("Code is incorrect");
        }

        byEmail.setVerified(true);
        userRepository.save(byEmail);
    }

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user, int id) {
        User userById = userRepository.findById(id).orElseThrow();
        userById.setName(user.getName());
        userById.setLastname(user.getLastname());
        userById.setBirthday(user.getBirthday());
        return userRepository.save(userById);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return null;
    }

    @Override
    @Transactional
    public void delete(int id) {

    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}

