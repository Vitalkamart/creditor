package ru.mart.andersen.creditor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mart.andersen.creditor.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
