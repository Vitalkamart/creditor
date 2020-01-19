package ru.mart.andersen.creditor.to;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreditOfferTo {
    private Long id;
    private UUID uid;
    private UUID orderUid;
    private String userName;
    private BigDecimal amount;
    private int creditRate;
    private int period;
    private LocalDateTime dateTime;

    public CreditOfferTo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public UUID getOrderUid() {
        return orderUid;
    }

    public void setOrderUid(UUID orderUid) {
        this.orderUid = orderUid;
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
        return "CreditOfferTo{" +
                "id=" + id +
                ", uid=" + uid +
                ", orderUid=" + orderUid +
                ", dateTime=" + dateTime +
                ", userLogin='" + userName + '\'' +
                ", amount=" + amount +
                ", creditRate=" + creditRate +
                ", period=" + period +
                '}';
    }
}
