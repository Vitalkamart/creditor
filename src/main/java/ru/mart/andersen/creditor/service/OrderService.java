package ru.mart.andersen.creditor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mart.andersen.creditor.repository.OrderRepository;
import ru.mart.andersen.creditor.to.OrderTo;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void save(OrderTo orderTo) {

    }
}
