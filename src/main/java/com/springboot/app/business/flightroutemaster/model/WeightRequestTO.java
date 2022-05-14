package com.springboot.app.business.flightroutemaster.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeightRequestTO {
	private String start;
	private String end;
	private List<String> otherDestinations;
}
