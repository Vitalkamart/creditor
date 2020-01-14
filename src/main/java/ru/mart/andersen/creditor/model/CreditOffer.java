package ru.mart.andersen.creditor.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "credit_offers")
public class CreditOffer extends AbstractBaseEntity {

    @Column(name = "uid")
    @NotNull
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID uid;

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
    private BigDecimal amount;

    @Column(name = "credit_rate")
    @NotNull
    @Size(min = 50, max = 240, message = "credit rate should be in range 5-24")
    private int creditRate;

    @Column(name = "period")
    @NotNull
    @Size(min = 1, max = 120, message = "period should be in range 0-120")
    private int period;

    public CreditOffer() {
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(int creditRate) {
        this.creditRate = creditRate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
