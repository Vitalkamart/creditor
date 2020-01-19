package ru.mart.andersen.creditor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mart.andersen.creditor.model.CreditOffer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditOfferRepository extends CrudRepository<CreditOffer, Long> {

    @Query("select c from CreditOffer c where c.order.uid=:uid")
    Optional<CreditOffer> findByOrderUid(@Param("uid") UUID uid);
}
