package com.springboot.app.reports;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("report")
public class ReportController {
	private final ReportService reportService;

	@GetMapping("/")
	public String showPassenger(Model model) {
		model.addAttribute("passenger", reportService.findAll());
		return "passenger";
	}

	@PostMapping("/report")
	public String generateReport(@RequestParam("date") String date, @RequestParam("fileFormat") String fileFormat)
			throws JRException, IOException {
		LocalDate localDate = LocalDate.parse(date);
		String fileLink = reportService.generateReport(localDate, fileFormat);
		return "redirect:/" + fileLink;
	}
}