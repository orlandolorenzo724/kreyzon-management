package com.oldigitalsolutions.management.service;

import com.oldigitalsolutions.management.model.SocialAccount;
import com.oldigitalsolutions.management.repository.SocialAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialAccountService extends BaseService {
    @Autowired
    private SocialAccountRepository socialAccountRepository;

    public List<SocialAccount> getSocialAccountsByProspectId(Long prospectId) {
        return socialAccountRepository.findByProspectId(prospectId);
    }

    public SocialAccount getSocialAccountById(Long id) {
        return socialAccountRepository.findById(id).orElse(null);
    }

    public SocialAccount createSocialAccount(SocialAccount socialAccount) {
        socialAccount.setId(idGenerator.generateSocialAccountId());
        return socialAccountRepository.save(socialAccount);
    }

    public SocialAccount updateSocialAccount(SocialAccount socialAccount) {
        return socialAccountRepository.save(socialAccount);
    }

    public void deleteSocialAccount(Long id) {
        socialAccountRepository.deleteById(id);
    }
}