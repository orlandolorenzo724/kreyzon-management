package com.oldigitalsolutions.management.controller;

import com.oldigitalsolutions.management.model.Email;
import com.oldigitalsolutions.management.service.EmailService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/emails")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<Email>> getEmails() {
        return ResponseEntity.ok(emailService.getEmails());
    }

    @GetMapping("/prospects/{prospectId}")
    public ResponseEntity<List<Email>> getEmailsByProspectId(@PathVariable String prospectId) {
        return ResponseEntity.ok(emailService.getEmailsByProspectId(Long.valueOf(prospectId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable Long id) {
        Email email = emailService.getEmailById(id);
        if (email == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(email);
    }

    @PostMapping
    public ResponseEntity<Email> createEmail(@RequestBody Email email) {
        return ResponseEntity.ok(emailService.createEmail(email));
    }

    @PutMapping
    public ResponseEntity<Email> updateEmail(@RequestBody Email email) {
        return ResponseEntity.ok(emailService.updateEmail(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        emailService.deleteEmail(id);
        return ResponseEntity.noContent().build();
    }
}
