package org.example.buysell.controllers;

import io.swagger.annotations.Api;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private MessageSource messageSource;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        String message = messageSource.getMessage("greeting.message", new Object[]{}, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(message);
    }
}
