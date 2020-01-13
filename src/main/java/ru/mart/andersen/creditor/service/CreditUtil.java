package ru.mart.andersen.creditor.service;

import ru.mart.andersen.creditor.util.exceptions.NoSuitableInterestException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CreditUtil {
    public static int findBestInterest(List<Integer> interests, int period, BigDecimal price, BigDecimal amount) {
        BigDecimal sum;

        BigDecimal tmpSum = null;
        int tmpSuitableIntrst = - 1;
        int index = interests.size() / 2;

        int start = 0;
        int end = interests.size() - 1;

        //loop for binary search index with closest sum without recursion
        //
        while (end >= start) {
            int currentIntrst = interests.get(index);
            sum = getSum(currentIntrst, period, amount);
            if (sum.compareTo(price) == 0) {
                return currentIntrst;
            } else if (sum.compareTo(price) < 0) {
                if (end == start) {
                    return currentIntrst;
                } else {
                    tmpSum = sum;
                    tmpSuitableIntrst = currentIntrst;
                    start = index + 1;
                    index += (end - start) / 2;
                }
            } else if (sum.compareTo(price) > 0) {
                if (end == start) {
                    if (tmpSum == null) {
                        throw new NoSuitableInterestException("there is no suitable interest");
                    } else {
                        return  tmpSuitableIntrst;
                    }
                } else {
                    end = index - 1;
                    index -= (end - start) / 2;
                }
            }
        }

        BigDecimal tmp = BigDecimal.valueOf(tmpSuitableIntrst)
                .setScale(1, BigDecimal.ROUND_DOWN)
                .divide(BigDecimal.TEN, BigDecimal.ROUND_HALF_DOWN);
        //System.out.println("best founded interest: " + tmp + "%" + " sum = " + tmpSum + " interest=" + tmpSuitableIntrst);
        return tmpSuitableIntrst;
    }

    public static BigDecimal getSum(int currentIntrst, int period, BigDecimal amount) {
        BigDecimal singlePay = calculateSinglePayment(currentIntrst, period, amount);

        return singlePay.multiply(BigDecimal.valueOf(period))
                .setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**
     *
     * @param interest
     * @param period
     * @param amount
     * @return
     */
    public static BigDecimal calculateSinglePayment(int interest, int period, BigDecimal amount) {
        if (interest < 50 || interest > 240) {
            throw new IllegalArgumentException("interest out of range: min = 5% (50), max = 24% (240) ");
        } else if (period < 1 || period > 120) {
            throw new IllegalArgumentException("period out of range: min = 1, max = 120 (months)");
        } else if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("credit amount can't be <= 0");
        }
        //System.out.println("interest=" + interest + " period=" + period + " amount=" + amount);

        // for 19,9% = 0,016583333
        BigDecimal currentInterest = BigDecimal.valueOf(interest)
                .setScale(10, BigDecimal.ROUND_DOWN)
                .divide(BigDecimal.valueOf(1000), RoundingMode.DOWN)
                .divide(BigDecimal.valueOf(period), RoundingMode.DOWN);

        // pay = amount * (interest + (interest / ((1 + interest) ^ period - 1)))
        // step1 = (1 + interest)
        // step2 = step1 ^ period
        // step3 = step2 - 1
        // step4 = interest / step3
        // step5 = interest + step4
        // step6 = amount * step5

        BigDecimal step1 = currentInterest.add(BigDecimal.ONE);
        BigDecimal step2 = step1.pow(period);
        BigDecimal step3 = step2.subtract(BigDecimal.ONE);
        BigDecimal step4 = currentInterest.divide(step3, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal step5 = currentInterest.add(step4);
        return amount.multiply(step5);
    }

    public static BigDecimal getOfferAmount(int priceInt, int discountInt) {
        if (discountInt > 100 || discountInt < 0) {
            throw new IllegalArgumentException("discount can't be out of range 0-100");
        } else if (priceInt < 0) {
            throw new IllegalArgumentException("discount can't be < 0");
        }

        BigDecimal price = BigDecimal.valueOf(priceInt);
        BigDecimal discount = BigDecimal.valueOf(discountInt);
        BigDecimal x = BigDecimal.valueOf(100)
                .subtract(discount)
                .setScale(2, BigDecimal.ROUND_HALF_DOWN);

        BigDecimal k = x.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_HALF_DOWN);
        return price.multiply(k);
    }
}
