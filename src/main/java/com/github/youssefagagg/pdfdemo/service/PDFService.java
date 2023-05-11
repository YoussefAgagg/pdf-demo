package com.github.youssefagagg.pdfdemo.service;

import com.github.youssefagagg.pdfdemo.entity.User;

import com.github.youssefagagg.pdfdemo.repo.UserRepository;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PDFService {
    private final TemplateService<User> userTemplateService;
    private final UserRepository userRepository;

    public ResponseEntity<Resource> generateUsersReportPDFHtml() throws DocumentException, IOException {

        var htmlTemplate = userTemplateService.generateHtmlTemplate(userRepository.findAll(), "user", "users");
        System.out.println(htmlTemplate);
        ITextRenderer renderer = new ITextRenderer();
        // BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        renderer.getFontResolver().addFont(ResourceUtils.getFile("classpath:arial.ttf").getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        renderer.setDocumentFromString(htmlTemplate);

        renderer.layout();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        renderer.createPDF(byteArrayOutputStream);
        renderer.finishPDF();

        ByteArrayResource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("users")
                                .build().toString())
                .body(resource);
    }

    public ResponseEntity<Resource> generateUsersReportPDFJasper() throws DocumentException, JRException, FileNotFoundException {


        // Set report parameters
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("Title", "User Data Report");


        // Create a JRBeanCollectionDataSource from the user data
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userRepository.findAll());
        JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.
                        compileReport(ResourceUtils.getFile("classpath:userdata.jrxml").getAbsolutePath()),
                parameters,
                dataSource);

        ByteArrayResource resource = new ByteArrayResource(JasperExportManager.exportReportToPdf(jasperPrint));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("user")
                                .build().toString())
                .body(resource);
    }
}
