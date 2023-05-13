package com.github.youssefagagg.pdfdemo.service;

import com.github.youssefagagg.pdfdemo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JasperPDFService implements PDFService {
    private final UserRepository userRepository;


    @Override
    public ResponseEntity<Resource> generateUsersReportPDF() throws Exception {

        // Set report parameters
        Map<String, Object> parameters = new HashMap<>();
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
