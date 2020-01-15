package ru.mart.andersen.creditor.to;

//import ru.mart.andersen.creditor.model.OrderItem;

import ru.mart.andersen.creditor.model.Item;

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
    @XmlElement(name = "item")
    private List<Item> items;

    public UserTo getUserTo() {
        return userTo;
    }

    public void setUserTo(UserTo userTo) {
        this.userTo = userTo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CartTo{" +
                "userTo=" + userTo +
                ", orderItems=" + items +
                '}';
    }
}
