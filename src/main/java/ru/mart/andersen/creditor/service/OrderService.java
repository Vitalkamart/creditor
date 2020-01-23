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
import static ru.mart.andersen.creditor.util.validators.OrderValidator.validateOrder;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(OrderTo orderTo) {
        Objects.requireNonNull(orderTo);

        Order order = getOrderFromTo(orderTo);
        validateOrder(order);

        return orderRepository.save(order);
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
