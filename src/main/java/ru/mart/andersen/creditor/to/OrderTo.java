package ru.mart.andersen.creditor.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.UUID;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderTo {

    @XmlElement
    private long id;

    @XmlElement(name = "order_uid")
    private UUID uid;

    @XmlElement(name = "total_price")
    private BigDecimal price;

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
