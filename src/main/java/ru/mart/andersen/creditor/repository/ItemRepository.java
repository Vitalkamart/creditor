package ru.mart.andersen.creditor.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mart.andersen.creditor.model.Item;

import java.util.UUID;

public interface ItemRepository extends CrudRepository<Item, UUID> {

}
