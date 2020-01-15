package ru.mart.andersen.creditor.repository;

import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.mart.andersen.creditor.config.CreditorPostgresqlContainer;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.model.User;

import static org.junit.jupiter.api.Assertions.*;


class OrderRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CreditorPostgresqlContainer.getInstance();

    @Autowired
    private OrderRepository orderRepository;

    private static Order order;

    @BeforeAll
    static void init() {
        order = new Order();

        User user = new User();
        user.setId(1L);
        order.setId("1");
        order.setDiscount(13);

    }


}