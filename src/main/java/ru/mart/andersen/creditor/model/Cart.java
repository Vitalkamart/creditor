package ru.mart.andersen.creditor.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@MappedSuperclass
public class Cart extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name="order_items",
            joinColumns={@JoinColumn(name="order_uid", referencedColumnName="UID")},
            inverseJoinColumns={@JoinColumn(name="item_uid", referencedColumnName="UID")})
    private List<Item> items;

    protected Cart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getOrderItems() {
        return items;
    }

    public void setOrderItems(List<Item> orderItems) {
        this.items = orderItems;
    }
}
