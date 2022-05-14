package com.springboot.app.services.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Flight {
	private int idvuelo;
	private int idaeronave_fk;
	private String origen;
	private String destino;
	private int idaeropuerto_origen_fk;
	private int idaeropuerto_destino_fk;
	private int idclima;
	private int idemergencia;
	private int idcompania_fk;
	private String regladevuelo;
	private String tipodevuelo;
	private String horasalidaestimada;
	private String horallegadaestimada;
	private boolean estado;
	private String ruta;
	private String pistaenusodespegue;
	private String pistaenusoaterrizaje;
	private int ltscombustibleestimado;
	private int ltscombustiblereal;
	private int lubricanteestimado;
	private int lubricantereal;
	private int kilometrajeestimado;
	private int kilometrajereal;
	private boolean verificaciones;
	private int totalpersonasabordo;
	private int pesoequipaje;
	private boolean emergencia;
	private String horasalidareal;
	private String horallegadareal;
	private String duracionestimada;
	private String duracionreal;
}
