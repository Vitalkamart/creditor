package ru.mart.andersen.creditor.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uid"})})
public class Product extends AbstractBaseEntity {

    @Column(name = "uid")
    @NotNull
    @Size(min = 4, max = 10, message = "product uid should contain 4-10 symbols")
    private String uid;

    @Column(name = "min_sum")
    @NotNull
    @DecimalMin(value = "0.00", message = "product minimal sum can't be negative")
    private BigDecimal minSum;

    @Column(name = "max_sum")
    @NotNull
    @DecimalMin(value = "0.00", message = "product maximum sum can't be negative")
    private BigDecimal maxSum;

    @Column(name = "min_rate")
    @NotNull
    @Range(min = 0, message = "product minimal credit rate can't be negative")
    private int minRate;

    @Column(name = "max_rate")
    @NotNull
    @Range(min = 0, message = "product maximum credit rate can't be negative")
    private int maxRate;

    @Column(name = "period")
    @NotNull
    @Range(min = 0, max = 120, message = "period should be in range 0-120")
    private int period;


    public Product() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getMinSum() {
        return minSum;
    }

    public void setMinSum(BigDecimal minSum) {
        this.minSum = minSum;
    }

    public BigDecimal getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(BigDecimal maxSum) {
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

    @Override
    public String toString() {
        return "Product{" +
                "uid='" + uid + '\'' +
                ", minSum=" + minSum +
                ", maxSum=" + maxSum +
                ", minRate=" + minRate +
                ", maxRate=" + maxRate +
                ", period=" + period +
                ", id=" + id +
                '}';
    }
}
