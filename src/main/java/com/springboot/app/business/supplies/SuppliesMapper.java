package com.springboot.app.business.supplies;

import org.springframework.stereotype.Component;

import com.springboot.app.business.supplies.model.SuppliesDE;
import com.springboot.app.business.supplies.model.SuppliesTO;

@Component
public class SuppliesMapper {

	public static SuppliesTO mapTo(SuppliesDE de) {
		SuppliesTO to = new SuppliesTO();
		to.setIdSupplies(de.getIdSupplies());
		to.setIdFlight(de.getIdFlight());
		to.setMenuEconomy(de.getMenuEconomy());
		to.setMenuBusiness(de.getMenuBusiness());
		to.setMenuVegetariano(de.getMenuVegetariano());
		to.setComoditiesEconomy(de.getComoditiesEconomy());
		to.setComoditiesBusiness(de.getComoditiesBusiness());

		return to;
	}
}
