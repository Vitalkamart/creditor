package ru.mart.andersen.creditor.to.converter;

import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.model.enums.Role;
import ru.mart.andersen.creditor.to.UserTo;

public class UserConverter {

    public static User getUserFromTo(UserTo userTo) {
        User user = new User();

        user.setId(userTo.getId());
        user.setLogin(userTo.getLogin());
        user.setName(userTo.getName());
        user.setPassword(userTo.getPassword());
        if (userTo.getRole() != null) {
            user.setRole(Role.valueOf(userTo.getRole()));
        }

        return user;
    }
}
