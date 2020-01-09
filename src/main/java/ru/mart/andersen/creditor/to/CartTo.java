package ru.mart.andersen.creditor.to;

import ru.mart.andersen.creditor.model.OrderItem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CartTo {

    @XmlElement(name = "user")
    private UserTo userTo;

    @XmlElementWrapper(name = "item_list")
    @XmlElement(name = "order_item")
    private List<OrderItem> orderItems;

    public UserTo getUserTo() {
        return userTo;
    }

    public void setUserTo(UserTo userTo) {
        this.userTo = userTo;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "CartTo{" +
                "userTo=" + userTo +
                ", orderItems=" + orderItems +
                '}';
    }
}
