package com.springboot.app.business.supplies.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuppliesTO {
	private int idSupplies;
	private String code;
	private int menuEconomy;
	private int menuBusiness;
	private int menuVegetariano;
	private int comoditiesEconomy;
	private int comoditiesBusiness;

}
