package ru.mart.andersen.creditor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mart.andersen.creditor.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Transactional
    Optional<User> findByLogin(String login);

    @Transactional
    Optional<User> deleteByLogin(String login);
}
