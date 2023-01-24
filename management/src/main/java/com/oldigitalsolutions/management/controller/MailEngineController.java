package com.oldigitalsolutions.management.controller;


import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.oldigitalsolutions.management.engine.MailEngineService;
import com.oldigitalsolutions.management.request.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/mail-engine")
public class MailEngineController {
    @Autowired
    private MailEngineService mailEngineService;

    @PostMapping
    public ResponseEntity<List<MailDto>> sendEmail() throws MailjetSocketTimeoutException, MailjetException {
        return ResponseEntity.ok(mailEngineService.sendEmail());
    }
}
