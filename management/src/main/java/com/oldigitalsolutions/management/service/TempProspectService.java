package com.oldigitalsolutions.management.service;

import com.oldigitalsolutions.management.model.Category;
import com.oldigitalsolutions.management.model.Prospect;
import com.oldigitalsolutions.management.model.SocialAccount;
import com.oldigitalsolutions.management.model.TempProspect;
import com.oldigitalsolutions.management.repository.TempProspectRepository;
import com.oldigitalsolutions.management.utils.Constant;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class TempProspectService extends BaseService {
    @Autowired
    private TempProspectRepository tempProspectRepository;

    public Void elaborateData() {
        List<TempProspect> tempProspectList = tempProspectRepository.findAll();
        for (TempProspect temp : tempProspectList) {
            log.info("Working on: " + temp.toString());

            if (StringUtils.isBlank(temp.getFullName()))
                temp.setFullName("");

            if (StringUtils.isBlank(temp.getContactNumber()))
                temp.setContactNumber("");

            Prospect prospect = Prospect
                    .builder()
                    .id(idGenerator.generateProspectId())
                    .name(temp.getFullName())
                    .company("")
                    .email(temp.getEmail())
                    .phone(temp.getContactNumber())
                    .stage(Constant.STAGE_NEW)
                    .category(temp.getCategory().toUpperCase(Locale.ROOT))
                    .notes("")
                    .contactable(Constant.CONTACTABLE_YES)
                    .dateAdded(LocalDateTime.now())
                    .build();
            prospectRepository.save(prospect);

            SocialAccount socialAccount = SocialAccount
                    .builder()
                    .id(idGenerator.generateSocialAccountId())
                    .prospect(prospect)
                    .username(temp.getUsername())
                    .platform(Constant.PLATFORM_INSTAGRAM)
                    .build();
            socialAccountRepository.save(socialAccount);

            if (!categoryRepository.existsById(prospect.getCategory())) {
                Category category = Category
                        .builder()
                        .name(prospect.getCategory().toUpperCase())
                        .build();
                categoryRepository.save(category);
            }
        }

        tempProspectRepository.deleteAll(tempProspectList);
        return null;
    }
}
