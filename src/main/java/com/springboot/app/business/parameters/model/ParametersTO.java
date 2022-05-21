package com.springboot.app.business.parameters.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParametersTO {
	private int id;
	private int gananciaPorPersona;
	private int costoLitroCombustible;
	private int costoLitroLubricante;
	private int costoInsumosPorPersona;
	private int promedioDePersonas;

}
