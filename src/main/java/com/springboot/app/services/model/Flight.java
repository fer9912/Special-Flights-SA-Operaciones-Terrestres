package com.springboot.app.services.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Flight {
	private int idvuelo;
	private String aeronave_matricula_fk;
	private String origenteorico_codiata_fk;
	private String origenreal_codiata_fk;
	private String destinoteorico_codiata_fk;
	private String destinoreal_codiata_fk;
	private int idcompania_fk;
	private int idestados_fk;
	private String rutateorica;
	private String rutareal;
	private String regladevuelo;
	private String tipodevuelo;
	private String fechadespegueestimado;
	private String horadespegueestimado;
	private String fechadespeguereal;
	private String horadespeguereal;
	private String fechaaterrizajeestimado;
	private String horaaterrizajeestimado;
	private String fechaaterrizajereal;
	private String horaaterrizajereal;
	private int ltscombustibleestimado;
	private int ltscombustiblereal;
	private int lubricanteestimado;
	private int lubricantereal;
	private int kilometrajeestimado;
	private int kilometrajereal;
	private Boolean verificaciones;
	private int totalpersonasabordo;
	private String duracionestimada;
	private String duracionreal;
	private int insumosconsumidos;
	private int pesocargadestino;
	private Boolean informado;
	private String estado;
	private String clima;
}
