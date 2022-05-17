package com.springboot.app.business.baggage.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaggageRequest {
	private String idEquipaje;
	private String idPasajero;
	private String idVuelo;

}
