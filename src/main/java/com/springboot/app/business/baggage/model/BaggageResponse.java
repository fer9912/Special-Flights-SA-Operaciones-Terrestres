package com.springboot.app.business.baggage.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaggageResponse {
	private String baggageId;
	private String flightId;
	private String baggageType;
	private String weight;
	private String status;
	private String isPassengerBaggage;
	private String docType;
	private String documentNumber;
}
