package ru.mart.andersen.creditor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.model.Product;
import ru.mart.andersen.creditor.repository.CreditOfferRepository;
import ru.mart.andersen.creditor.repository.OrderRepository;
import ru.mart.andersen.creditor.repository.ProductRepository;
import ru.mart.andersen.creditor.to.OrderTo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ru.mart.andersen.creditor.service.CreditUtil.*;
import static ru.mart.andersen.creditor.to.converter.OrderConverter.getOrderFromTo;

@Service
public class CreditService {

    @Autowired
    private CreditOfferRepository creditOfferRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Optional<CreditOffer> getCreditOffer(Order order) {
        Objects.requireNonNull(order);

        Optional<Product> product = productRepository.findBySum(order.getPrice());
        CreditOffer creditOffer = null;

        if (product.isPresent()) {
            creditOffer = getCreditOffer(order,  product.get());
            save(creditOffer);
        }

        return Optional.ofNullable(creditOffer);
    }

    private CreditOffer getCreditOffer(Order order, Product product) {
        CreditOffer creditOffer = new CreditOffer();

        creditOffer.setOrder(order);
        creditOffer.setAmount(getOfferAmount(order.getPrice(), order.getDiscount()));
        setCreditRate(creditOffer, product.getMinRate(), product.getMaxRate());
        return creditOffer;
    }

    @Transactional
    public void manageOrder(OrderTo orderTo) {
        Objects.requireNonNull(orderTo);

        Order order = getOrderFromTo(orderTo);
        orderRepository.save(order);

        Optional<CreditOffer> creditOffer = getCreditOffer(order);
    }

    private void setCreditRate(CreditOffer creditOffer, int minRate, int maxRate) {
        List<Integer> rates = new ArrayList<>();
        for (int i = minRate; i < maxRate; i++) {
            rates.add(i);
        }
        BigDecimal price = creditOffer.getOrder().getPrice();
        BigDecimal amount = creditOffer.getAmount();
        int period = creditOffer.getPeriod();

        creditOffer.setCreditRate(findBestInterest(rates, period, price, amount));
    }

    private void save(CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
    }

}
