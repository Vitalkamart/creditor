package ru.mart.andersen.creditor.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.mart.andersen.creditor.TestUtils.USER_1;
import static ru.mart.andersen.creditor.TestUtils.USER_2;

class UserServiceTest {

    @MockBean
    private static UserRepository userRepository;

    @BeforeAll
    static void init() {
        userRepository = mock(UserRepository.class);

        when(userRepository.findByLogin("login1")).thenReturn(Optional.of(USER_1));
        when(userRepository.findByLogin("login2")).thenReturn(Optional.of(USER_2));
        when(userRepository.findById(1L)).thenReturn(Optional.of(USER_1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(USER_2));
        when(userRepository.save(any(User.class))).thenReturn(USER_1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"login1", "login2", "login3"})
    @DisplayName("get user by login")
    void findByLogin(String login) {
        switch (login) {
            case "login1":
                assertEquals(USER_1, userRepository.findByLogin(login).get());
                break;
            case "login2":
                assertEquals(USER_2, userRepository.findByLogin(login).get());
                break;
            default:
                assertEquals(Optional.empty(), userRepository.findByLogin(login));
        }
    }

    @Test
    @DisplayName("save user")
    void save() {
        assertEquals(USER_1, userRepository.save(USER_1));
        assertEquals(USER_1, userRepository.save(USER_2));
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    @DisplayName("get user by id")
    void getById(Long id) {
        if (id == 1L) {
            assertEquals(USER_1, userRepository.findById(id).get());
        } else if (id == 2L) {
            assertEquals(USER_2, userRepository.findById(id).get());
        } else {
            assertEquals(Optional.empty(), userRepository.findById(id));
        }
    }
}
