package com.springboot.app.reports;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.passenger.PassengerService;
import com.springboot.app.business.passenger.model.PassengerTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@CrossOrigin("*")
@RestController
@RequestMapping("report")
public class ReportController {

	@Autowired
	private PassengerService service;
	static Logger log = Logger.getLogger(ReportController.class);

	@ExceptionHandler(Exception.class)
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public ResponseEntity<byte[]> previewReport() throws JRException, IOException {

		try {
			log.info("busco el jasper");
			InputStream jasperStream = this.getClass().getResourceAsStream("/report/passenger.jasper");
			Map<String, Object> params = new HashMap<>();
			log.info("levanto pasajeros");
			List<PassengerTO> passengers = this.service.getAll();
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(passengers);
			log.info("llena reporte");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, beanColDataSource);

			log.info("conf salida");
			final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = "report.pdf";

			headers.add("content-disposition", "inline;filename=" + filename);
			log.info("todo bien");

			return new ResponseEntity<byte[]>(outStream.toByteArray(), headers, HttpStatus.OK);
		} catch (Exception e) {
			log.info("todo mal" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public void downloadReport(HttpServletResponse response) throws JRException, IOException {

		try {
			log.info("busco el jasper");
			InputStream jasperStream = this.getClass().getResourceAsStream("/report/passenger.jasper");
			Map<String, Object> params = new HashMap<>();
			log.info("levanto pasajeros");
			List<PassengerTO> passengers = this.service.getAll();
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(passengers);
			log.info("llena reporte");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, beanColDataSource);
			log.info("conf salida");
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "attachment; filename=report.pdf");
			log.info("carga la salida");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			log.info("todo bien");
		} catch (Exception e) {
			log.info("todo mal" + e);
			e.printStackTrace();

		}

	}
}