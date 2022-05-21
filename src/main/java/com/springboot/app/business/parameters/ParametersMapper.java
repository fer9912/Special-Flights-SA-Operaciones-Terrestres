package com.springboot.app.business.parameters;

import com.springboot.app.business.parameters.model.ParametersDE;
import com.springboot.app.business.parameters.model.ParametersTO;

public class ParametersMapper {

	public static ParametersTO mapTo(ParametersDE de) {
		ParametersTO to = new ParametersTO();
		to.setId(de.getId());
		to.setPromedioDePersonas(de.getPromedioDePersonas());
		to.setCostoInsumosPorPersona(de.getCostoInsumosPorPersona());
		to.setCostoLitroCombustible(de.getCostoLitroCombustible());
		to.setCostoLitroLubricante(de.getCostoLitroLubricante());
		to.setGananciaPorPersona(de.getGananciaPorPersona());
		return to;
	}

	public static ParametersDE mapDE(ParametersTO to) {
		ParametersDE de = new ParametersDE();
		de.setId(1);
		de.setPromedioDePersonas(to.getPromedioDePersonas());
		de.setCostoInsumosPorPersona(to.getCostoInsumosPorPersona());
		de.setCostoLitroCombustible(to.getCostoLitroCombustible());
		de.setCostoLitroLubricante(to.getCostoLitroLubricante());
		de.setGananciaPorPersona(to.getGananciaPorPersona());
		return de;
	}

}
