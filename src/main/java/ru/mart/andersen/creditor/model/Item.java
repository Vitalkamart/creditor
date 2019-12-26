package ru.mart.andersen.creditor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "items")
public class Item extends AbstractBaseEntity{

    @Column(name = "name")
    @NotNull
    @Size(
            min = 5,
            max = 255,
            message = "item name should contain from 5 to 255 symbols")
    private String name;

    @Column(name = "price")
    @NotNull
    @Size(min = 0, message = "price can't be negative")
    private int price;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
