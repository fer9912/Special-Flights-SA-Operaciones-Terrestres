package com.springboot.app.business.user.model;

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
@Table(name = "role")
@Component
public class RoleDE implements Serializable {

	private static final long serialVersionUID = 6094407212788597216L;

	@Id
	@Column(name = "id", nullable = true)
	private String id;

	@Column(name = "name", length = 45, nullable = false)
	private String name;

	@Column(name = "rute_fligth_master", length = 10, nullable = true)
	private boolean ruteFligthMaster;

	@Column(name = "uav", length = 10, nullable = true)
	private boolean uav;

	@Column(name = "flights", length = 10, nullable = true)
	private boolean flights;

	@Column(name = "check_flight", length = 10, nullable = true)
	private boolean checkFlight;

	@Column(name = "check_commissariat", length = 10, nullable = true)
	private boolean checkCommissariat;

	@Column(name = "baggage", length = 10, nullable = true)
	private boolean baggage;

	@Column(name = "parameters", length = 10, nullable = true)
	private boolean parameters;

	@Column(name = "reports", length = 10, nullable = true)
	private boolean reports;

}
