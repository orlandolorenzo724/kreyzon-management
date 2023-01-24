package com.oldigitalsolutions.management.service;

import com.oldigitalsolutions.management.repository.*;
import com.oldigitalsolutions.management.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class BaseService {
    @Autowired
    protected IDGenerator idGenerator;

    @Autowired
    protected ProspectRepository prospectRepository;

    @Autowired
    protected EmailRepository emailRepository;

    @Autowired
    protected SocialAccountRepository socialAccountRepository;

    @Autowired
    protected SetupRepository setupRepository;

    @Autowired
    protected CategoryRepository categoryRepository;
}
