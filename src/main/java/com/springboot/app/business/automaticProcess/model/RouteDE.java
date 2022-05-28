package com.springboot.app.business.automaticProcess.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "route")
@Component
public class RouteDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "cod_vuelo", length = 45, nullable = false)
	private String codVuelo;

	@Column(name = "origin", length = 3, nullable = false)
	private String origin;

	@Column(name = "destination", length = 3, nullable = false)
	private String destination;

	@Column(name = "stop_1", length = 3)
	private String stop1;

	@Column(name = "stop_2", length = 3)
	private String stop2;

	@Column(name = "day", length = 45, nullable = false)
	private String day;

	@Column(name = "hour", length = 45, nullable = false)
	private String hour;

	@Column(name = "date", nullable = false)
	private Date date;

}
