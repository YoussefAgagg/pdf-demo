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
    public ResponseEntity<Resource> generateUsersReportPDFHtml() throws Exception {
        return pdfService.generateUsersReportPDF();

    }
}
