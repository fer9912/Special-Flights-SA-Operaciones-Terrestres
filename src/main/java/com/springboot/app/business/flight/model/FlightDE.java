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
	@Column(name = "code", length = 100, nullable = true)
	private String code;

	@Column(name = "origin", length = 45, nullable = false)
	private String origin;

	@Column(name = "destination", length = 45, nullable = false)
	private String destination;

	@Column(name = "date", length = 20, nullable = true)
	private Date date;

	@Column(name = "landing_date", length = 20, nullable = true)
	private Date landingDate;

	@Column(name = "route", length = 100, nullable = true)
	private String route;

	@Column(name = "status", length = 45, nullable = true)
	private String status;

	@Column(name = "company", length = 100, nullable = true)
	private String company;

	@Column(name = "day", length = 20, nullable = true)
	private String day;

	@Column(name = "hour", length = 100, nullable = true)
	private String hour;

	@Column(name = "aircraft", nullable = true)
	private int aircraft;

	@Column(name = "aircrafts", nullable = true)
	private String aircrafts;

	@Column(name = "people_estimate", nullable = true)
	private int peopleEstimate;

	@Column(name = "duration", nullable = true)
	private int duration;

	@Column(name = "landing_hour", length = 100, nullable = true)
	private String landingHour;

	@Column(name = "flight_type", length = 100, nullable = true)
	private String flightType;

	@Column(name = "flight_rule", length = 100, nullable = true)
	private String flightRule;
}
