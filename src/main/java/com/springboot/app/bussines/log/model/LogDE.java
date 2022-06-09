package com.springboot.app.bussines.log.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "process_log")
@Component
public class LogDE implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8739123745486271380L;

	@Id
	@Column(name = "process", length = 45)
	private String processName;

	@Column(name = "last_exec", length = 255)
	private LocalDateTime lastExecution;

}
