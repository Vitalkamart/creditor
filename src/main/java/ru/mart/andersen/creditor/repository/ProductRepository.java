package ru.mart.andersen.creditor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mart.andersen.creditor.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p from Product p WHERE p.minSum<=:sum AND p.maxSum>=:sum")
    Optional<Product> findBySum(int sum);
}
