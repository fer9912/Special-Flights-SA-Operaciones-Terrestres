package com.springboot.app.reports;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.springboot.app.business.passenger.model.PassengerTO;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
	String generateReport(LocalDate localDate, String fileFormat) throws JRException, IOException;

	List<PassengerTO> findAll();
}