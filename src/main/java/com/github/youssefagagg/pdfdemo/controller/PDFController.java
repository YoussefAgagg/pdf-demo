package com.github.youssefagagg.pdfdemo.controller;

import com.github.youssefagagg.pdfdemo.service.PDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PDFController {
    private final PDFService pdfService;

    @GetMapping("/users/report")
    public ResponseEntity<Resource> generateUsersReportPDFHtml() throws Exception {
        return pdfService.generateUsersReportPDF();

    }
}
