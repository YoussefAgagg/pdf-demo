package com.github.youssefagagg.pdfdemo.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface PDFService {
    ResponseEntity<Resource> generateUsersReportPDF() throws Exception;
}
