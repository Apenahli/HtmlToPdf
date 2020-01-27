package com.example;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

@RequestMapping(value = "/html2pdf", method = RequestMethod.POST, produces = "application/pdf")
public class FlyingSoucerTestService {
	public void test() throws DocumentException, IOException, com.itextpdf.text.DocumentException {
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setPrefix("META-INF/pdfTemplates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("XHTML");
	    templateResolver.setCharacterEncoding("UTF-8");

	    TemplateEngine templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);

	    Context ctx = new Context();
	    ctx.setVariable("message", "I don't want to live on this planet anymore");
	    String htmlContent = templateEngine.process("messageTpl", ctx);

	    ByteOutputStream os = new ByteOutputStream();
	    ITextRenderer renderer = new ITextRenderer();
	    ITextFontResolver fontResolver = renderer.getFontResolver();

	    ClassPathResource regular = new ClassPathResource("/META-INF/fonts/LiberationSerif-Regular.ttf");
	    fontResolver.addFont(regular.getURL().toString(), BaseFont.IDENTITY_H, true);

	    renderer.setDocumentFromString(htmlContent);
	    renderer.layout();
	    renderer.createPDF(os);

	    byte[] pdfAsBytes = os.getBytes();
	    os.close();

	    FileOutputStream fos = new FileOutputStream(new File("/tmp/message.pdf"));
	    fos.write(pdfAsBytes);
	    fos.close();
	  }
}
