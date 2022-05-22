package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.springboot.app.business.supplies.model.SuppliesDE;

public interface SuppliesRepository extends JpaRepository<SuppliesDE, Long>, JpaSpecificationExecutor<SuppliesDE> {

	SuppliesDE findByCode(String code);
}
