package ru.mart.andersen.creditor.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
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

    @Column(name = "user_name")
    @NotNull
    @Size(min = 0, max = 100, message = "user login should have 5-100 symbols")
    private String userName;

    @Column(name = "amount", precision = 10, scale = 2)
    @NotNull(message = "credit amount can't be null")
    @DecimalMin(value = "0", message = "price can't be negative")
    private BigDecimal amount;

    @Column(name = "credit_rate")
    @NotNull
    @Range(min = 50, max = 240, message = "credit rate should be in range 5-24")
    private int creditRate;

    @Column(name = "period")
    @NotNull
    @Range(min = 1, max = 120, message = "period should be in range 0-120")
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "CreditOffer{" +
                "uid=" + uid +
                ", order=" + order +
                ", dateTime=" + dateTime +
                ", userLogin='" + userName + '\'' +
                ", amount=" + amount +
                ", creditRate=" + creditRate +
                ", period=" + period +
                ", id=" + id +
                '}';
    }
}
