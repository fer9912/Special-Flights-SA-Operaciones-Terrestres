package com.springboot.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.springboot.app.bussines.log.model.LogDE;

public interface LogRepository extends JpaRepository<LogDE, String>, JpaSpecificationExecutor<LogDE> {

	Optional<LogDE> findByProcessName(String id);

}
