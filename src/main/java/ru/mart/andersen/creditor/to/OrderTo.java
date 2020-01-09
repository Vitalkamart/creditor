package ru.mart.andersen.creditor.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderTo {

    @XmlElement
    private long id;

    @XmlElement(name = "order_uid")
    private String uid;

    @XmlElement(name = "total_price")
    private int price;

    @XmlElement(name = "cart")
    private CartTo cartTo;

    @XmlElement
    private int discount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public CartTo getCartTo() {
        return cartTo;
    }

    public void setCartTo(CartTo cartTo) {
        this.cartTo = cartTo;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderTo{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", price=" + price + "\n" +
                ", cartTo=" + cartTo +
                ", discount=" + discount +
                '}';
    }
}