package com.pmone.demo.rest.repository;

import com.pmone.demo.rest.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
