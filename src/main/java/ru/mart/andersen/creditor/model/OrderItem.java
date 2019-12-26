package ru.mart.andersen.creditor.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_items",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_id", "item_id"})})
public class OrderItem extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;

    @OneToOne
    @NotNull
    private Item item;

    @Column(name = "count")
    @NotNull
    private int count;

    public OrderItem() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
