package ru.mart.andersen.creditor.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uid"})})
public class Order extends Cart {

    @Column(name = "uid")
    @NotNull
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID uid;

    @Column(name = "price")
    @NotNull
    @Size(min = 0, message = "price can't be negative or null")
    private BigDecimal price;

    @Column(name = "discount")
    @NotNull
    @Size(min = 0, max = 100, message = "discount should be in range 0-100")
    private int discount;

    public Order() {
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
