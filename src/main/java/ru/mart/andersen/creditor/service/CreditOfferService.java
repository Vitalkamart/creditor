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
import ru.mart.andersen.creditor.to.CreditOfferTo;
import ru.mart.andersen.creditor.to.OrderTo;
import ru.mart.andersen.creditor.util.exceptions.CreditOfferValidationException;
import ru.mart.andersen.creditor.util.exceptions.NoSuchCreditOfferException;
import ru.mart.andersen.creditor.util.exceptions.NoSuitableProductException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static ru.mart.andersen.creditor.to.converter.CreditOfferConverter.getToFromCreditOffer;
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

    public CreditOffer getCreditOffer(Order order, String userName) {
        System.out.println("CreditOfferService getCreditOffer(Order) method with order=" + order);
        Optional<Product> product = productRepository.findBySum(order.getPrice());
        CreditOffer creditOffer;

        if (product.isPresent()) {
            System.out.println("CreditOfferService getCreditOffer(Order) method product is found: " + product.get());
            creditOffer = getCreditOffer(order,  product.get());
            creditOffer.setUserName(userName);
            return creditOffer;
        } else {
            throw new NoSuitableProductException(
                    "there is no suitable product for order price=" + order.getPrice());
        }

    }

    private CreditOffer getCreditOffer(Order order, Product product) {
        CreditOffer creditOffer = new CreditOffer();

        creditOffer.setOrder(order);
        creditOffer.setPeriod(product.getPeriod());
        creditOffer.setAmount(getOfferAmount(order.getPrice(), order.getDiscount()));
        setCreditRate(creditOffer, product.getMinRate(), product.getMaxRate());
        return creditOffer;
    }

    @Transactional
    public UUID manageOrderTo(OrderTo orderTo) {
        System.out.println("CreditOfferService manage(orderTo) method with orderTo=" + orderTo);
        Objects.requireNonNull(orderTo);

        Order order = getOrderFromTo(orderTo);
        System.out.println("CreditOfferService manage(orderTo) method with order=" + order);
        System.out.println();
        validateOrder(order);
        System.out.println("CreditOfferService manage(orderTo) orderTo is valid!!!");
        System.out.println();
        UUID orderUid = orderRepository.save(order).getUid();
        System.out.println("CreditOfferService manage(orderTo) order with uid=" + orderUid + " is saved");
        System.out.println();
        String userName = orderTo.getCartTo().getUserTo().getName();
        CreditOffer creditOffer = getCreditOffer(order, userName);
        creditOffer.setUid(UUID.randomUUID());
        creditOffer.setDateTime(LocalDateTime.now());
        System.out.println("CreditOfferService manage(orderTo) method with creditOffer=" + creditOffer);
        System.out.println();
        creditOfferRepository.save(creditOffer);
        System.out.println("CreditOfferService manage(orderTo) creditOffer is saved!!!");
        System.out.println();
        return orderUid;
    }

    private void setCreditRate(CreditOffer creditOffer, int minRate, int maxRate) {
        System.out.println("CreditOfferService setCreditRate(CreditOffer, minRate, maxRate)" +
                " method with creditOffer=" + creditOffer + " minRate=" + minRate + " maxRate=" + maxRate);
        List<Integer> rates = new ArrayList<>();
        for (int i = minRate; i < maxRate; i++) {
            rates.add(i);
        }
        BigDecimal price = creditOffer.getOrder().getPrice();
        BigDecimal amount = creditOffer.getAmount();
        int period = creditOffer.getPeriod();

        creditOffer.setCreditRate(findBestInterest(rates, period, price, amount));
    }

    public CreditOfferTo getCreditOfferByOrderUid(String orderUidString) {
        try {
            UUID orderUid = UUID.fromString(orderUidString);
            Optional<CreditOffer> creditOffer = creditOfferRepository.findByOrderUid(orderUid);
            if (creditOffer.isPresent()) {
                return getToFromCreditOffer(creditOffer.get());
            } else {
                throw new NoSuchCreditOfferException(
                        "there is no credit offer for order with uid=" + orderUidString);
            }
        } catch (IllegalArgumentException ex) {
            throw new CreditOfferValidationException(
                    "order uid does not conform to the string representation of uuid");
        }
    }

    public CreditOffer getCreditOfferById(long id) {
        Optional<CreditOffer> selected = creditOfferRepository.findById(id);
        if (selected.isPresent()) {
            return selected.get();
        } else {
            throw new NoSuchCreditOfferException("there is no creditOffer with id=" + id);
        }
    }
}
