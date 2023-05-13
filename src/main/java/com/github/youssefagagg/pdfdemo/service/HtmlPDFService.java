package com.github.youssefagagg.pdfdemo.service;

import com.github.youssefagagg.pdfdemo.entity.User;
import com.github.youssefagagg.pdfdemo.repo.UserRepository;
import com.lowagie.text.pdf.BaseFont;
import lombok.AllArgsConstructor;
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

@Service
@AllArgsConstructor
public class HtmlPDFService implements PDFService {
    private final TemplateService<User> userTemplateService;
    private final UserRepository userRepository;


    @Override
    public ResponseEntity<Resource> generateUsersReportPDF() throws Exception {

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
}
