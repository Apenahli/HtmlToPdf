package com.example.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Html2PdfServiceImpl implements Html2PdfService {

	private TemplateEngine templateEngine;

	@Override
	public InputStreamResource generateInvoice(Map<String, Object> data) {
		Context context = new Context();
		context.setVariables(data);
		String html = templateEngine.process("invoice", context);
		try {
			HtmlConverter.convertToPdf(html, new FileOutputStream("target/test.pdf"));
			return new InputStreamResource(new FileInputStream("target/test.pdf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
