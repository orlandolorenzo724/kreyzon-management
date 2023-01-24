package com.oldigitalsolutions.management.utils;

import com.oldigitalsolutions.management.model.Email;
import com.oldigitalsolutions.management.model.Prospect;
import com.oldigitalsolutions.management.model.SocialAccount;
import com.oldigitalsolutions.management.repository.EmailRepository;
import com.oldigitalsolutions.management.repository.ProspectRepository;
import com.oldigitalsolutions.management.repository.SocialAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IDGenerator {
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private ProspectRepository prospectRepository;

    @Autowired
    private SocialAccountRepository socialAccountRepository;

    public Long generateEmailId() {
        Optional<Email> emailOptional = emailRepository.findFirstByOrderByIdDesc();
        if (emailOptional.isEmpty())
            return 1L;

        return emailOptional.get().getId() + 1;
    }

    public Long generateProspectId() {
        Optional<Prospect> prospectOptional = prospectRepository.findFirstByOrderByIdDesc();
        if (prospectOptional.isEmpty())
            return 1L;

        return prospectOptional.get().getId() + 1;
    }

    public Long generateSocialAccountId() {
        Optional<SocialAccount> socialAccountOptional = socialAccountRepository.findFirstByOrderByIdDesc();
        if (socialAccountOptional.isEmpty())
            return 1L;

        return socialAccountOptional.get().getId() + 1;
    }
}
