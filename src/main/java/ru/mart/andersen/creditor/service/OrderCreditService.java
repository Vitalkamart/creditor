package ru.mart.andersen.creditor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.to.OrderTo;

import java.util.UUID;

@Service
public class OrderCreditService {
    private final OrderService orderService;
    private final CreditOfferService creditOfferService;

    public OrderCreditService(OrderService orderService, CreditOfferService creditOfferService) {
        this.orderService = orderService;
        this.creditOfferService = creditOfferService;
    }

    @Transactional
    public UUID save(OrderTo orderTo) {
        Order order = orderService.save(orderTo);
        String username = orderTo.getCartTo().getUserTo().getName();
        creditOfferService.manageOrder(order, username);
        return order.getUid();
    }

    public OrderTo get(UUID uid) {
        return orderService.get(uid);
    }
}
