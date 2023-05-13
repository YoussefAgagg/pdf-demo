package com.github.youssefagagg.pdfdemo.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PDFService {
    ResponseEntity<Resource> generateUsersReportPDF() throws Exception;
}
