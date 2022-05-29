package com.springboot.app.reports;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.app.business.passenger.PassengerService;
import com.springboot.app.business.passenger.model.PassengerTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("report")
public class ReportController {

	final Logger log = LoggerFactory.getLogger(this.getClass());
	final ModelAndView model = new ModelAndView();

	@Autowired
	PassengerService service;

	@GetMapping(value = "/view")
	public ModelAndView viewReport() {
		log.info("Preparing the pdf report via jasper.");
		try {
			createPdfReport(service.getAll());
			log.info("File successfully saved at the given path.");
		} catch (final Exception e) {
			log.error("service.getAll()");
			e.printStackTrace();
		}

		model.setViewName("welcome");
		return model;
	}

	private void createPdfReport(final List<PassengerTO> passengers) throws JRException {

		InputStream stream = this.getClass().getResourceAsStream("/passenger.jrxml");
		System.out.println(passengers);

		JasperReport report = JasperCompileManager.compileReport(stream);

		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(passengers);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "GROPS");

		JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

		String filePath = "\\";

		JasperExportManager.exportReportToPdfFile(print, filePath + "passengers_report.pdf");
	}
}