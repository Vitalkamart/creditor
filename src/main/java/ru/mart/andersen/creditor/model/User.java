package ru.mart.andersen.creditor.model;

import ru.mart.andersen.creditor.model.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",  uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
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

//    @ManyToOne(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinTable(
//            name="user_role",
//            joinColumns={@JoinColumn(name="user_id", referencedColumnName="ID")},
//            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="ID")})

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", id=" + id +
                '}';
    }
}
