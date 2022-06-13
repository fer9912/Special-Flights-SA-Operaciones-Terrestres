package com.springboot.app.business.baggage.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "cargo")
@Component
public class BaggageDE implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -6077602061243220714L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cargo", nullable = true)
	private Long id;

	@Column(name = "tipo", nullable = true)
	private String tipo;

	@Column(name = "weight", nullable = true)
	private Integer weight;

	@Column(name = "id_passenger", nullable = true)
	private Integer idPassenger;

	@Column(name = "id_vuelo", nullable = true)
	private String idVuelo;

	@Column(name = "estado_vuelo", nullable = true)
	private String estadoVuelo;

	@Column(name = "categoria", nullable = true)
	private String categoria;

	@Column(name = "estado_carga", nullable = true)
	private String estadoCarga;

}
