package com.pmone.demo.rest.repository;

import com.pmone.demo.rest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
