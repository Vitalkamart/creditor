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
private static final Logger log = LoggerFactory.getLogger(UserService.class);

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

    //    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public boolean save(UserTo userTo) {
        log.debug("UserService save(UserTo) with userTo=" + userTo);
        User user = getUserFromTo(userTo);
        log.debug("UserService save(UserTo) getting user from to, user=" + user);
        validateUser(user);
        log.debug("UserService save(UserTo) user is validated");

        if (user.isNew()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.USER);
            log.debug("UserService save(UserTo) password is encoded and role is set");
        }

        if (!findByLogin(user.getLogin()).isPresent()) {
            userRepository.save(user);
            log.debug("UserService save(UserTo) user is saved" + user);
            return true;
        }
        return false;
    }
}
