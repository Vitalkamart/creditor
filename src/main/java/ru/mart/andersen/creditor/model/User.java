package ru.mart.andersen.creditor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"login"})})
public class User extends AbstractBaseEntity {

    @Column(name = "name")
    @NotNull
    @Size(min = 5, max = 100, message = "name should contain 5 to 100 symbols")
    private String name;

    @Column(name = "login")
    @NotNull
    @Size(min = 5, max = 100, message = "login should contain 5 to 100 symbols")
    private String login;

    @Column(name = "password")
    @NotNull
    @Size(min = 60, max = 60)
    private String password;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
