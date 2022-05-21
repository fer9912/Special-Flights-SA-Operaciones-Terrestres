package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.parameters.model.ParametersDE;

@Repository
public interface ParametersRepository
		extends JpaRepository<ParametersDE, Long>, JpaSpecificationExecutor<ParametersDE> {

	ParametersDE findById(int id);
}