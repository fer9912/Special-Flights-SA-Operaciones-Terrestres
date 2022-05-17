package com.springboot.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.springboot.app.business.baggage.model.BaggageDE;

public interface BaggageRepository extends JpaRepository<BaggageDE, Long>, JpaSpecificationExecutor<BaggageDE> {

	List<BaggageDE> findByIdPassengerOrderByIdVuelo(int id);

	List<BaggageDE> findByIdVueloOrderByIdPassenger(String id);

	List<BaggageDE> findByIdVueloAndIdPassengerOrderByIdVuelo(String idVuelo, int idPassenger);
}
