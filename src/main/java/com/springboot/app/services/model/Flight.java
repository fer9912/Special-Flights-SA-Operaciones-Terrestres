package com.springboot.app.services.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Flight {
	private String codvuelo;
	private String estado;
	private String aeronave_matricula_fk;
	private String modeloaeronave;
	private String origenteorico_codiata;
	private String origenreal_codiata;
	private String destinoteorico_codiata;
	private String destinoreal_codiata;
	private String siglacompania;
	private String nombrecompania;
	private String rutateorica;
	private String rutareal;
	private String regladevuelo;
	private String tipodevuelo;
	private String diadespegue;
	private Date fechadespegueestimado;
	private String horadespegueestimado;
	private String fechadespeguereal;
	private String horadespeguereal;
	private Date fechaaterrizajeestimado;
	private String horaaterrizajeestimado;
	private String fechaaterrizajereal;
	private String horaaterrizajereal;
	private String climadestino;
	private int gradostemperaturadestino;
	private String velocidadvientokm;
	private int ltscombustibleestimado;
	private int ltscombustiblereal;
	private int lubricanteestimado;
	private int lubricantereal;
	private int kilometrajeestimado;
	private int kilometrajereal;
	private Boolean checkin;
	private Boolean controlcabina;
	private int totalpersonasabordo;
	private String duracionestimada;
	private String duracionreal;
	private int insumosconsumidos;
	private int pesocargaorigen;
	private int pesocargadestino;

}
