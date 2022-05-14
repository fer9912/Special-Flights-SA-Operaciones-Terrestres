package com.springboot.app.business.flightroutemaster.model;

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
@Table(name = "flight_history")
@Component
public class FlightHistoryDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "start", length = 8, nullable = false)
	private String start;

	@Column(name = "end", length = 8, nullable = false)
	private String end;

	@Column(name = "distance", nullable = false)
	private int distance;

	@Column(name = "duration", nullable = false)
	private int duration;

	@Column(name = "passengers", nullable = false)
	private int passengers;

	@Column(name = "date", nullable = false)
	private Date date;

}
