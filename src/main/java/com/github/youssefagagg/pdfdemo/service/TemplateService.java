package com.github.youssefagagg.pdfdemo.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TemplateService<T> {

    private final SpringTemplateEngine templateEngine;

    public String generateHtmlTemplate(List<T> t, String templateName, String variableName) {
        Context context = new Context();
        context.setVariable(variableName, t);
        return templateEngine.process(templateName, context);

    }

}
