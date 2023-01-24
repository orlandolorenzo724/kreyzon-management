package com.oldigitalsolutions.management.service;

import com.oldigitalsolutions.management.model.Setup;
import org.springframework.stereotype.Service;

@Service
public class SetupService extends BaseService {

    public Setup getSetup() {
        return setupRepository.findAll().get(0);
    }
}
