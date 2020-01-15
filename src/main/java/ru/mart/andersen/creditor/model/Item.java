package ru.mart.andersen.creditor.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "items", uniqueConstraints = {@UniqueConstraint(columnNames = {"uid"})})
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "uid")
    @NotNull
    @org.hibernate.annotations.Type(type="pg-uuid")
    @XmlElement(name = "uid")
    private UUID uid;

    @Column(name = "id")
    @NotNull
    @Size(
            max = 255,
            message = "item name should contain maximum 255 symbols")
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_uid", nullable = false)
    @NotNull
    @org.hibernate.annotations.Type(type="pg-uuid")
    @XmlTransient
    private Order order;

    @Column(name = "name")
    @NotNull
    @Size(
            min = 5,
            max = 255,
            message = "item name should contain from 5 to 255 symbols")
    @XmlElement(name = "name")
    private String name;

    @Column(name = "price")
    @NotNull
    @Size(min = 0, message = "price can't be negative")
    @XmlElement(name = "price")
    private BigDecimal price;

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
