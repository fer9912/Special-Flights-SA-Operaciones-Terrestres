package com.springboot.app.business.flightroutemaster.model;

import java.util.List;

import com.springboot.app.business.airport.model.AirportTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Way {
	private String endString;
	private List<AirportTO> route;
	private int weight;
}