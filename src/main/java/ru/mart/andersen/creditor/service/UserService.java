package ru.mart.andersen.creditor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.model.enums.Role;
import ru.mart.andersen.creditor.repository.UserRepository;
import ru.mart.andersen.creditor.to.UserTo;

import java.util.Collections;
import java.util.Optional;

import static ru.mart.andersen.creditor.to.converter.UserConverter.getUserFromTo;
import static ru.mart.andersen.creditor.util.validators.UserValidator.validateUser;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //    @Transactional(readOnly=true, noRollbackFor=Exception.class)
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public boolean save(UserTo userTo) {
        User user = getUserFromTo(userTo);
        validateUser(user);

        if (user.isNew()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.USER);
        }

        if (!findByLogin(user.getLogin()).isPresent()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
