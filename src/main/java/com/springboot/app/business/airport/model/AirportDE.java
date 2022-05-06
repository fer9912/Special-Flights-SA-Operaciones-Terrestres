package com.springboot.app.business.airport.model;

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
@Table(name = "airport")
@Component
public class AirportDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "IATA", length = 3, nullable = false)
	private String iata;

	@Column(name = "country", length = 100, nullable = false)
	private String country;

	@Column(name = "city", length = 100, nullable = false)
	private String city;

	@Column(name = "state", length = 100, nullable = false)
	private String state;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "OACI", length = 4, nullable = false)
	private String oaci;

}
