package com.oldigitalsolutions.management.controller;

import com.oldigitalsolutions.management.model.Prospect;
import com.oldigitalsolutions.management.service.ProspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/prospects")
public class ProspectController {
    @Autowired
    private ProspectService prospectService;

    @GetMapping
    public ResponseEntity<List<Prospect>> getAllProspects() {
        return ResponseEntity.ok(prospectService.getAllProspects());
    }

    @GetMapping("/last-prospects")
    public ResponseEntity<List<Prospect>> getLastProspects() {
        return ResponseEntity.ok(prospectService.getLastProspects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prospect> getProspectById(@PathVariable Long id) {
        Prospect prospect = prospectService.getProspectById(id);
        if (prospect == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prospect);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Prospect>> filterProspects(@RequestBody Prospect prospect) {
        return ResponseEntity.ok(prospectService.filterProspects(prospect));
    }

    @PostMapping
    public ResponseEntity<Prospect> createProspect(@RequestBody Prospect prospect) {
        return ResponseEntity.ok(prospectService.createProspect(prospect));
    }

    @PutMapping
    public ResponseEntity<Prospect> updateProspect(@RequestBody Prospect prospect) {
        return ResponseEntity.ok(prospectService.updateProspect(prospect));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProspect(@PathVariable Long id) {
        prospectService.deleteProspect(id);
        return ResponseEntity.noContent().build();
    }
}