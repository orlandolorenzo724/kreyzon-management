package com.oldigitalsolutions.management.service;

import com.oldigitalsolutions.management.model.Email;
import com.oldigitalsolutions.management.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService extends BaseService {
    @Autowired
    private EmailRepository emailRepository;

    public List<Email> getEmails() {
        return emailRepository.findAll();
    }

    public List<Email> getEmailsByProspectId(Long prospectId) {
        return emailRepository.findByProspectId(prospectId);
    }

    public Email getEmailById(Long id) {
        return emailRepository.findById(id).orElse(null);
    }

    public Email createEmail(Email email) {
        email.setId(idGenerator.generateEmailId());
        return emailRepository.save(email);
    }

    public Email updateEmail(Email email) {
        return emailRepository.save(email);
    }

    public void deleteEmail(Long id) {
        emailRepository.deleteById(id);
    }
}