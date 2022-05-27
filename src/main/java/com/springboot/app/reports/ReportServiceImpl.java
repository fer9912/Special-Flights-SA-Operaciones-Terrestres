package com.springboot.app.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.springboot.app.business.passenger.PassengerService;
import com.springboot.app.business.passenger.model.PassengerTO;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@RequiredArgsConstructor
public abstract class ReportServiceImpl implements ReportService {
	private final PassengerService passengerService;

	private JasperPrint getJasperPrint(List<PassengerTO> passengers, String resourceLocation)
			throws FileNotFoundException, JRException {
		File file = ResourceUtils.getFile(resourceLocation);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(passengers);
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, dataSource);

		return jasperPrint;
	}

	private Path getUploadPath(String fileFormat, JasperPrint jasperPrint, String fileName)
			throws IOException, JRException {
		String uploadDir = StringUtils.cleanPath("./generated-reports");
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		// generate the report and save it in the just created folder
		if (fileFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, uploadPath + fileName);
		}

		return uploadPath;
	}

	private String getPdfFileLink(String uploadPath) {
		return uploadPath + "/" + "passenger.pdf";
	}

	@Override
	public String generateReport(LocalDate localDate, String fileFormat) throws JRException, IOException {
		List<PassengerTO> passengers = passengerService.getAll();
		// load the file and compile it
		String resourceLocation = "classpath:passenger.jrxml";
		JasperPrint jasperPrint = getJasperPrint(passengers, resourceLocation);
		// create a folder to store the report
		String fileName = "/" + "passenger.pdf";
		Path uploadPath = getUploadPath(fileFormat, jasperPrint, fileName);
		// create a private method that returns the link to the specific pdf file

		return getPdfFileLink(uploadPath.toString());
	}

	@Override
	public List<PassengerTO> findAll() {
		return passengerService.getAll();
	}
}