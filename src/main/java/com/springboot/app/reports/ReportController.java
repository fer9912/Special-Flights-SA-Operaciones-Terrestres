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
@RequestMapping(value = "/report")
public class ReportController {

	final Logger log = LoggerFactory.getLogger(this.getClass());
	final ModelAndView model = new ModelAndView();

	@Autowired
	PassengerService service;

	// Method to display the index page of the application.
	@GetMapping(value = "/get")
	public ModelAndView index() {
		log.info("Showing the welcome page.");
		model.setViewName("welcome");
		return model;
	}

	// Method to create the pdf report via jasper framework.
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
		// Returning the view name as the index page for ease.
		model.setViewName("welcome");
		return model;
	}

	// Method to create the pdf file using the employee list datasource.
	private void createPdfReport(final List<PassengerTO> passengers) throws JRException {
		// Fetching the .jrxml file from the resources folder.
		final InputStream stream = this.getClass().getResourceAsStream("/passenger.jrxml");
		System.out.println(passengers);
		// Compile the Jasper report from .jrxml to .japser
		final JasperReport report = JasperCompileManager.compileReport(stream);

		// Fetching the employees from the data source.
		final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(passengers);

		// Adding the additional parameters to the pdf.
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "GROPS");

		// Filling the report with the employee data and additional parameters
		// information.
		final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

		// Users can change as per their project requrirements or can take it as request
		// input requirement.
		// For simplicity, this tutorial will automatically place the file under the
		// "c:" drive.
		// If users want to download the pdf file on the browser, then they need to use
		// the "Content-Disposition" technique.
		final String filePath = "\\";
		// Export the report to a PDF file.
		JasperExportManager.exportReportToPdfFile(print, filePath + "passengers_report.pdf");
	}
}