package ru.mart.andersen.creditor.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    protected AbstractBaseEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !Hibernate.getClass(this).equals(Hibernate.getClass(o))) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;

        return id != null && id.equals(that.id);
    }
}
