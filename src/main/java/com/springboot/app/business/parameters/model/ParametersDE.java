package com.springboot.app.business.parameters.model;

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
@Table(name = "parameters")
@Component
public class ParametersDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "ganancia_por_persona", nullable = false)
	private int gananciaPorPersona;

	@Column(name = "costo_litro_combustible", nullable = false)
	private int costoLitroCombustible;

	@Column(name = "costo_litro_lubricante", nullable = false)
	private int costoLitroLubricante;

	@Column(name = "costo_insumos_por_persona", nullable = false)
	private int costoInsumosPorPersona;

	@Column(name = "promedio_de_personas", nullable = false)
	private int promedioDePersonas;

}
