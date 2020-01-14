package ru.mart.andersen.creditor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "products",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uid"})})
public class Product extends AbstractBaseEntity {

    @Column(name = "uid")
    @NotNull
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID uid;

    @Column(name = "min_sum")
    @NotNull
    @Size(min = 0, message = "product minimal sum can't be negative")
    private int minSum;

    @Column(name = "max_sum")
    @NotNull
    @Size(min = 0, message = "product maximum sum can't be negative")
    private int maxSum;

    @Column(name = "min_rate")
    @NotNull
    @Size(min = 0, message = "product minimal credit rate can't be negative")
    private int minRate;

    @Column(name = "max_rate")
    @NotNull
    @Size(min = 0, message = "product maximum credit rate can't be negative")
    private int maxRate;

    @Column(name = "period")
    @NotNull
    @Size(min = 0, max = 120, message = "period should be in range 0-120")
    private int period;


    public Product() {
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public int getMinSum() {
        return minSum;
    }

    public void setMinSum(int minSum) {
        this.minSum = minSum;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    public int getMinRate() {
        return minRate;
    }

    public void setMinRate(int minRate) {
        this.minRate = minRate;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(int maxRate) {
        this.maxRate = maxRate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
