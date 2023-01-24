package com.oldigitalsolutions.management.controller;

import com.oldigitalsolutions.management.service.TempProspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/temp-prospect")
public class TempProspectController {
    @Autowired
    private TempProspectService tempProspectService;

    @PostMapping
    public ResponseEntity<Void> elaborateData() {
        return ResponseEntity.ok(tempProspectService.elaborateData());
    }
}
