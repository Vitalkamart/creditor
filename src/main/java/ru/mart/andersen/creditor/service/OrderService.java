package ru.mart.andersen.creditor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.repository.OrderRepository;
import ru.mart.andersen.creditor.to.OrderTo;
import ru.mart.andersen.creditor.util.exceptions.NoSuchOrderException;
import ru.mart.andersen.creditor.util.validators.OrderValidator;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static ru.mart.andersen.creditor.to.converter.OrderConverter.getOrderFromTo;
import static ru.mart.andersen.creditor.to.converter.OrderConverter.getToFromOrder;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CreditOfferService creditOfferService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setCreditOfferService(CreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }

    @Transactional
    public UUID save(OrderTo orderTo) {
        System.out.println("OrderService save(orderTo) method with orderTo=" + orderTo);
        return creditOfferService.manageOrderTo(orderTo);
    }

    public OrderTo get(UUID uid) {
        Optional<Order> selected = orderRepository.findByUid(uid);
        if (selected.isPresent()) {
            return getToFromOrder(selected.get());
        } else {
            throw new NoSuchOrderException("there is no order with that uuid");
        }
    }
}
