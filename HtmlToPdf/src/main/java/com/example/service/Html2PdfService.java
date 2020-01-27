package com.example.service;

import java.util.Map;
import org.springframework.core.io.InputStreamResource;

public interface Html2PdfService {

	InputStreamResource generateInvoice(Map<String, Object> data);

}
