package com.springboot.app.services.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrewXFlight {
	private String idvuelo_fk;
	private String idpersonal_fk;
	private String aeropuerto_codiata;
	private String nombre;
	private String apellido;
	private String mail;
	private Boolean documentacionenorden;
	private String posicion;
	private Boolean activo;

}
