package com.github.youssefagagg.pdfdemo.controller;

import com.github.youssefagagg.pdfdemo.service.PDFService;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PDFController {
    private final PDFService pdfService;

    @GetMapping("/html/users-pdf-generator")
    public ResponseEntity<Resource> generateUsersReportPDFHtml() throws DocumentException, IOException {
        return pdfService.generateUsersReportPDFHtml();

    }
    @GetMapping("/jasper/users-pdf-generator")
    public ResponseEntity<Resource> generateUsersReportPDFJasper() throws DocumentException, JRException, FileNotFoundException {
        return pdfService.generateUsersReportPDFJasper();

    }
}
