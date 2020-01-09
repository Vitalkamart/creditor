package ru.mart.andersen.creditor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mart.andersen.creditor.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
