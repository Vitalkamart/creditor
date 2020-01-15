package ru.mart.andersen.creditor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mart.andersen.creditor.model.Order;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {

    @Transactional
    Optional<Order> findByUid(UUID uuid);

    @Transactional
    Optional<Order> deleteByUid(UUID uuid);

    @Query("delete from Order o where o.id=:id")
    void deleteById(@Param("id") String id);
}
