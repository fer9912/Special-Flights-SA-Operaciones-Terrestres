package com.springboot.app.business.flight.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "flight")
@Component
public class FlightDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "id_flight", nullable = false)
	private int idFlight;

	@Column(name = "origin", length = 45, nullable = false)
	private String origin;

	@Column(name = "destination", length = 45, nullable = false)
	private String destination;

	@Column(name = "departure", length = 20, nullable = true)
	private Date departure;

	@Column(name = "arrival", length = 20, nullable = true)
	private Date arrival;

	@Column(name = "route", length = 45, nullable = true)
	private String route;

	@Column(name = "status", length = 45, nullable = true)
	private String status;

	@Column(name = "aircraft", length = 30, nullable = true)
	private String aircraft;

}
