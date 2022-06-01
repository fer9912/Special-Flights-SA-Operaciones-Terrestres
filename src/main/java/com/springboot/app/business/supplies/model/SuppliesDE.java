package com.springboot.app.business.supplies.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "supplies")
@Component
public class SuppliesDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "id_supplies", nullable = true)
	private int idSupplies;

	@Column(name = "id_flight", length = 45, nullable = false)
	private int idFlight;

	@Column(name = "menu_economy", nullable = true)
	private int menuEconomy;

	@Column(name = "menu_business", nullable = true)
	private int menuBusiness;

	@Column(name = "menu_vegetariano", nullable = true)
	private int menuVegetariano;

	@Column(name = "comodities_economy", nullable = true)
	private int comoditiesEconomy;

	@Column(name = "comodities_business", nullable = true)
	private int comoditiesBusiness;

}
