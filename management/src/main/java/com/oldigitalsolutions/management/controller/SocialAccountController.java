package com.oldigitalsolutions.management.controller;


import com.oldigitalsolutions.management.model.SocialAccount;
import com.oldigitalsolutions.management.service.SocialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/social-accounts")
public class SocialAccountController {
    @Autowired
    private SocialAccountService socialAccountService;

    @GetMapping("/prospects/{prospectId}")
    public ResponseEntity<List<SocialAccount>> getSocialAccountsByProspectId(@PathVariable Long prospectId) {
        return ResponseEntity.ok(socialAccountService.getSocialAccountsByProspectId(prospectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialAccount> getSocialAccountById(@PathVariable Long id) {
        SocialAccount socialAccount = socialAccountService.getSocialAccountById(id);
        if (socialAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(socialAccount);
    }

    @PostMapping
    public ResponseEntity<SocialAccount> createSocialAccount(@RequestBody SocialAccount socialAccount) {
        return ResponseEntity.ok(socialAccountService.createSocialAccount(socialAccount));
    }

    @PutMapping
    public ResponseEntity<SocialAccount> updateSocialAccount(@RequestBody SocialAccount socialAccount) {
        return ResponseEntity.ok(socialAccountService.updateSocialAccount(socialAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocialAccount(@PathVariable Long id) {
        socialAccountService.deleteSocialAccount(id);
        return ResponseEntity.noContent().build();
    }
}