package com.springboot.app.business.baggage.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaggageResponse {
	private String id;
	private String idVuelo;
	private String tipo;
	private String peso;
	private String estado;
	private String cargaPasajero;
	private String tipoDoc;
	private String doc;
}
