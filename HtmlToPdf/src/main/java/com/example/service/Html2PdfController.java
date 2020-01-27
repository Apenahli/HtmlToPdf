package com.example.service;

import java.util.Map;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Html2PdfController {

	private  Html2PdfService pdfService;

//	@RequestMapping(value = "/html2pdf", method = RequestMethod.POST, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> html2pdf(@RequestBody Map<String, Object> data) {

		InputStreamResource resource = pdfService.generateInvoice(data);
		if (resource != null) {
			return ResponseEntity.ok().body(resource);
		} else {
			return new ResponseEntity<InputStreamResource>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
