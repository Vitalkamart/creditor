package ru.mart.andersen.creditor.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uid"})})
public class Order {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "uid")
    @NotNull
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID uid;

    @Column(name = "id")
    @NotNull
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_uid", referencedColumnName = "uid")
    private List<Item> items;

    @Column(name = "price", precision = 10, scale = 2)
    @NotNull
//    @Size(min = 0, message = "price can't be negative or null")
    @DecimalMin(value = "0", message = "price can't be negative")
    private BigDecimal price;

    @Column(name = "discount")
    @NotNull
    @Range(min = 5, max = 100, message = "discount should be in range 0-100")
    private int discount;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getOrderItems() {
        return items;
    }

    public void setOrderItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "uid=" + uid +
                ", id='" + id + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", items=" + items +
                '}';
    }
}
