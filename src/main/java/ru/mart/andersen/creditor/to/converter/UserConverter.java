package ru.mart.andersen.creditor.to.converter;

import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.to.UserTo;

public class UserConverter {

    public static UserTo getUserToFromUser(User user) {
        UserTo userTo = new UserTo();

        userTo.setId(user.getId());
        userTo.setLogin(user.getLogin());
        userTo.setName(user.getName());

        return userTo;
    }

    public static User getUserFromTo(UserTo userTo) {
        User user = new User();

        user.setId(userTo.getId());
        user.setLogin(userTo.getLogin());
        user.setName(userTo.getName());

        return user;
    }
}
