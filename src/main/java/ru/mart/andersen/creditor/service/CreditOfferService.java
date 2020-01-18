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

import static ru.mart.andersen.creditor.util.CreditUtil.*;
import static ru.mart.andersen.creditor.to.converter.OrderConverter.getOrderFromTo;
import static ru.mart.andersen.creditor.util.validators.OrderValidator.validateOrder;

@Service
public class CreditOfferService {

//    private final CreditOfferRepository creditOfferRepository;
//    private final OrderRepository orderRepository;
//    private final ProductRepository productRepository;
//
//    public CreditOfferService(CreditOfferRepository creditOfferRepository,
//                              OrderRepository orderRepository,
//                              ProductRepository productRepository) {
//        this.creditOfferRepository = creditOfferRepository;
//        this.orderRepository = orderRepository;
//        this.productRepository = productRepository;
//    }

    private CreditOfferRepository creditOfferRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setCreditOfferRepository(CreditOfferRepository creditOfferRepository) {
        this.creditOfferRepository = creditOfferRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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
        validateOrder(order);

        orderRepository.save(order);
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
