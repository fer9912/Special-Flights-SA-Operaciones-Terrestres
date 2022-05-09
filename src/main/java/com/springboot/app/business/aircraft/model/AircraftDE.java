package com.springboot.app.business.aircraft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "aircraft")
@Component
public class AircraftDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "code", length = 30, nullable = false)
	private String code;

	@Column(name = "model", length = 50, nullable = false)
	private String model;

	@Column(name = "fuel_consumption", nullable = false)
	private int fuelConsumption;

	@Column(name = "passenger_capacity", nullable = false)
	private int passengerCapacity;

	@Column(name = "weight_tolerance", nullable = false)
	private int weightTolerance;

	@Column(name = "crew", length = 100, nullable = false)
	private String crew;

}
