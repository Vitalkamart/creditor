package ru.mart.andersen.creditor.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "order_items",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_id", "item_id"})})
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItem extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull
    @XmlTransient
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

    @Override
    public String toString() {
        return "OrderItem{" +
                "order=" + order +
                ", item=" + item +
                ", count=" + count +
                ", id=" + id +
                '}';
    }
}
