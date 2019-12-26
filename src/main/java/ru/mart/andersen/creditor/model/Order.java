package ru.mart.andersen.creditor.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "orders",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uid"})})
public class Order extends Cart {

    @Column(name = "uid")
    @NotNull
    @Size(
            min = 4,
            max = 10,
            message = "product uid should contain from 5 to 10 symbols")
    private String uid;

    @Column(name = "price")
    @NotNull
    @Size(min = 0, message = "price can't be negative or null")
    private int price;

    @Column(name = "discount")
    @NotNull
    @Size(min = 0, max = 100, message = "discount should be in range 0-100")
    private int discount;

    public Order() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
