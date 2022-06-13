package com.springboot.app.services.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Baggage {
	public int idCarga;
	public String codigo;
	public int envio;
	public String codigoCarga;
	public String codigoPasajero;
	public String tipo;
	public int peso;
	public String tag;
	public String fechaCreacion;
	public String nombreCreador;
	public String fechaModificacion;
	public String nombreModificador;
	public boolean activo;
	public String estadoCarga;
	public String codigoVuelo;

}
