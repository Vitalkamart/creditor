package ru.mart.andersen.creditor.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "roles",   uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})})
public class Role extends AbstractBaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 3)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
