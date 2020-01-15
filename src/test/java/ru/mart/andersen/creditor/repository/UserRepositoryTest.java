package ru.mart.andersen.creditor.repository;

import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.mart.andersen.creditor.config.CreditorPostgresqlContainer;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.model.enums.Role;
import ru.mart.andersen.creditor.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CreditorPostgresqlContainer.getInstance();

    @Autowired
    private UserRepository userRepository;

    private static User user;



    @BeforeAll
    static void init() {
        user = new User();
        user.setPassword("$2a$10$RK56TyFl9Td2WI7rXI/.0ue/KRMpe42HO/bv.DsQKDKp9EzSQxHUW");
        user.setName("User Userovich Testov");
        user.setLogin("login100");
        user.setRole(Role.USER);

    }

    @Test
    @DisplayName("save and get user")
    void aTest() {
        userRepository.save(user);
        User selected = userRepository.findByLogin(user.getLogin()).get();
        assertEquals(user.getLogin(), selected.getLogin());
    }

    @Test
    @DisplayName("delete user")
    void cTest() {
        boolean isDeleted = userRepository.deleteByLogin(user.getLogin()).isPresent();
        boolean isFound = userRepository.findByLogin(user.getLogin()).isPresent();

        assertTrue(isDeleted);
        assertFalse(isFound);
    }
}
