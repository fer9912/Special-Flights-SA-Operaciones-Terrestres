package com.springboot.app.business.passenger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.passenger.model.PassengerTO;
import com.springboot.app.reports.ReportController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@CrossOrigin("*")
@RestController
@RequestMapping("passenger")
public class PassengerController {
	@Autowired
	private PassengerService service;
	static Logger log = Logger.getLogger(ReportController.class);

	@GetMapping("/get")
	public ResponseEntity<?> getPassenger(@RequestParam(value = "id") int id) {
		try {
			PassengerTO passenger = this.service.getPassenger(id);
			return ResponseEntity.ok(passenger);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid or empty passenger id");
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getPassengers() {
		try {
			List<PassengerTO> passengers = this.service.getAll();
			return ResponseEntity.ok(passengers);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error to get  passengers");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody PassengerTO passenger) {
		try {
			PassengerTO response = this.service.savePassenger(passenger);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error to save passenger");
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
