package ru.mart.andersen.creditor.util;

import ru.mart.andersen.creditor.util.exceptions.NoSuitableInterestException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

import static ru.mart.andersen.creditor.util.ValidationUtil.*;

public class CreditUtil {
    /**
     * Finds such an interest from given array the total payment sum would be closest (less or equal) to
     * the given price with
     * @param interests array of interests
     * @param period count of payments
     * @param price total price of the order
     * @param amount credit amount
     * @return best suitable
     */
    public static int findBestInterest(List<Integer> interests,
                                       int period,
                                       BigDecimal price,
                                       BigDecimal amount) {
        Objects.requireNonNull(interests);
        validatePeriod(period);
        validatePrice(price);
        validateAmount(amount);

        BigDecimal sum;

        BigDecimal tmpSum = null;
        int tmpSuitableInterest = - 1;
        int index = interests.size() / 2;

        int start = 0;
        int end = interests.size() -1 ;

        //loop for binary search index with closest sum without recursion
        while (end >= start) {
            int currentInterest = interests.get(index);

            validateCreditRate(currentInterest);

            sum = getSum(currentInterest, period, amount);
            if (sum.compareTo(price) == 0) {
                return currentInterest;
            } else if (sum.compareTo(price) < 0) {
                if (end == start) {
                    return currentInterest;
                } else if ((end - start) <= 2) {
                    start += 1;
                    index = start;
                } else {
                    tmpSum = sum;
                    tmpSuitableInterest = currentInterest;
                    start = index + 1;
                    index += (end - start) / 2;
                }
            } else if (sum.compareTo(price) > 0) {
                if (end == start) {
                    if (tmpSum == null) {
                        throw new NoSuitableInterestException("there is no suitable interest");
                    } else {
                        return  tmpSuitableInterest;
                    }
                } else {
                    end = index - 1;
                    int dif = end - start;
                    if (dif <= 2) {
                        end -= 1;
                        index = end;
                    } else {
                        index -= dif / 2;
                    }
                }
            }
        }

        return tmpSuitableInterest;
    }


    public static BigDecimal getSum(int currentInterest, int period, BigDecimal amount) {
        BigDecimal singlePay = calculateSinglePayment(currentInterest, period, amount);

        return singlePay.multiply(BigDecimal.valueOf(period))
                .setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**
     *
     * @param creditRate
     * @param period
     * @param amount
     * @return
     */
    public static BigDecimal calculateSinglePayment(int creditRate, int period, BigDecimal amount) {
        validateCreditRate(creditRate);
        validatePeriod(period);
        validateAmount(amount);

        //System.out.println("credit rate=" + creditRate + " period=" + period + " amount=" + amount);

        // for 19,9% and 12 month period = 0,016583333
        BigDecimal currentInterest = getInterestFromCreditRateAndPeriod(creditRate, period);

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

    public static BigDecimal getOfferAmount(BigDecimal price, int discountInt) {
        validateDiscount(discountInt);
        validatePrice(price);

        BigDecimal discount = BigDecimal.valueOf(discountInt);
        BigDecimal x = BigDecimal.valueOf(100)
                .subtract(discount)
                .setScale(2, BigDecimal.ROUND_HALF_DOWN);

        BigDecimal k = x.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_HALF_DOWN);
        return price.multiply(k);
    }

    public static BigDecimal getInterestFromCreditRateAndPeriod(int creditRate, int period) {
        validateCreditRate(creditRate);
        validatePeriod(period);

        return BigDecimal.valueOf(creditRate)
                .setScale(10, BigDecimal.ROUND_DOWN)
                .divide(BigDecimal.valueOf(1000), RoundingMode.DOWN)
                .divide(BigDecimal.valueOf(period), RoundingMode.DOWN);
    }
}
