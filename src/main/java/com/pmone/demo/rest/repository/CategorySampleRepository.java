package com.pmone.demo.rest.repository;

import com.pmone.demo.rest.model.CategorySample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategorySampleRepository extends JpaRepository<CategorySample, Long> {

  @Query(value = "Select * from Category_Sample c where c.name like %:place%", nativeQuery = true)
  List<CategorySample> findByPlaceContaining(String place);
}
