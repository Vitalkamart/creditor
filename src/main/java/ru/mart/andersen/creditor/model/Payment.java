package ru.mart.andersen.creditor.model;

import java.math.BigDecimal;

public class Payment {
    private int month;
    private BigDecimal remainingBalance;
    private BigDecimal principalPayment;
    private BigDecimal interestPayment;
    private BigDecimal payment;

    public Payment() {
    }

    public Payment(int month,
                   BigDecimal remainingBalance,
                   BigDecimal principalPayment,
                   BigDecimal interestPayment,
                   BigDecimal payment) {
        this.month = month;
        this.remainingBalance = remainingBalance;
        this.principalPayment = principalPayment;
        this.interestPayment = interestPayment;
        this.payment = payment;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public BigDecimal getPrincipalPayment() {
        return principalPayment;
    }

    public void setPrincipalPayment(BigDecimal principalPayment) {
        this.principalPayment = principalPayment;
    }

    public BigDecimal getInterestPayment() {
        return interestPayment;
    }

    public void setInterestPayment(BigDecimal interestPayment) {
        this.interestPayment = interestPayment;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "month=" + month +
                ", remainingBalance=" + remainingBalance +
                ", principalPayment=" + principalPayment +
                ", interestPayment=" + interestPayment +
                ", payment=" + payment +
                '}';
    }
}
