package com.pmone.demo.rest.repository;

import com.pmone.demo.rest.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
