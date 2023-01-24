package com.oldigitalsolutions.management.service;

import com.oldigitalsolutions.management.model.Email;
import com.oldigitalsolutions.management.model.Prospect;
import com.oldigitalsolutions.management.model.SocialAccount;
import com.oldigitalsolutions.management.repository.specification.ProspectSpecification;
import com.oldigitalsolutions.management.utils.Constant;
import com.oldigitalsolutions.management.utils.SearchCriteria;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectService extends BaseService {

    public List<Prospect> getAllProspects() {
        return prospectRepository.findAllOrderByIdDesc();
    }

    public List<Prospect> getLastProspects() {
        return prospectRepository.findLastProspects(Constant.NUMBER_OF_RETURNED_PROSPECTS);
    }

    public List<Prospect> filterProspects(Prospect prospect) {
        ProspectSpecification emailSpecification = null;
        ProspectSpecification categorySpecification = null;
        ProspectSpecification stageSpecification = null;

        if (StringUtils.isNotBlank(prospect.getEmail()))
            emailSpecification = new ProspectSpecification(new SearchCriteria("email", ":", prospect.getEmail()));

        if (StringUtils.isNotBlank(prospect.getCategory()))
            categorySpecification = new ProspectSpecification(new SearchCriteria("category", ":", prospect.getCategory()));

        if (StringUtils.isNotBlank(prospect.getStage()))
            stageSpecification = new ProspectSpecification(new SearchCriteria("stage", ":", prospect.getStage()));


        List<Prospect> prospectList = prospectRepository
                .findAll(
                    Specification
                            .where(emailSpecification)
                            .and(categorySpecification)
                            .and(stageSpecification)
                );

        return prospectList;
    }

    public Prospect getProspectById(Long id) {
        return prospectRepository.findById(id).orElse(null);
    }

    public Prospect createProspect(Prospect prospect) {
        prospect.setId(idGenerator.generateProspectId());
        return prospectRepository.save(prospect);
    }

    public Prospect updateProspect(Prospect prospect) {
        return prospectRepository.save(prospect);
    }

    public void deleteProspect(Long id) {
        List<SocialAccount> socialAccountList = socialAccountRepository.findByProspectId(id);
        socialAccountRepository.deleteAll(socialAccountList);

        List<Email> emailList = emailRepository.findByProspectId(id);
        emailRepository.deleteAll(emailList);

        prospectRepository.deleteById(id);
    }
}