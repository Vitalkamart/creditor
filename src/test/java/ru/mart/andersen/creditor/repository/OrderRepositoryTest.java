package ru.mart.andersen.creditor.repository;

import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.mart.andersen.creditor.config.CreditorPostgresqlContainer;
import ru.mart.andersen.creditor.model.Order;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mart.andersen.creditor.TestUtils.getTestOrder;


@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class OrderRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CreditorPostgresqlContainer.getInstance();

    @Autowired
    private OrderRepository orderRepository;

    private static Order testOrder;

    @BeforeAll
    static void init() {
        testOrder = getTestOrder();
    }


    @Test
    @DisplayName("save and get order")
    void aTest() {
        UUID uuid = orderRepository.save(testOrder).getUid();
        testOrder.setUid(uuid);
        Order selected = orderRepository.findByUid(uuid).get();

        assertEquals(testOrder.toString(), selected.toString());
    }

    @Test
    @DisplayName("delete order")
    void bTest() {
        boolean isDeleted = orderRepository.deleteByUid(testOrder.getUid()).isPresent();
        boolean isFound = orderRepository.findByUid(testOrder.getUid()).isPresent();
        assertTrue(isDeleted);
        assertFalse(isFound);
    }
}