package ru.mart.andersen.creditor.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_offers",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uid"})})
public class CreditOffer extends AbstractBaseEntity {

    @Column(name = "uid")
    @NotNull
    @Size(
            min = 4,
            max = 10,
            message = "product uid should contain from 5 to 10 symbols")
    private String uid;

    @OneToOne
    @NotNull
    private Order order;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "user_login")
    @NotNull
    @Size(min = 0, max = 100, message = "user login should have 5-100 symbols")
    private String userLogin;

    @Column(name = "amount")
    @NotNull
    @Size(min = 0, message = "credit amount can't be negative or null")
    private int amount;

    @Column(name = "credit_rate")
    @NotNull
    @Size(min = 5, max = 24, message = "credit rate should be in range 5-24")
    private int creditRate;

    public CreditOffer() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(int creditRate) {
        this.creditRate = creditRate;
    }
}