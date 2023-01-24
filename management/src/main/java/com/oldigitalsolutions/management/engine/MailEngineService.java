package com.oldigitalsolutions.management.engine;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.oldigitalsolutions.management.model.Email;
import com.oldigitalsolutions.management.model.Prospect;
import com.oldigitalsolutions.management.model.SocialAccount;
import com.oldigitalsolutions.management.request.MailDto;
import com.oldigitalsolutions.management.service.BaseService;
import com.oldigitalsolutions.management.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailEngineService extends BaseService {

    @Autowired
    private MailSender mailSender;

    public List<MailDto> sendEmail() throws MailjetSocketTimeoutException, MailjetException {
        List<MailDto> mailDtoList = new ArrayList<>();

        List<Prospect> prospectList = prospectRepository.findProspectListByStage(Constant.STAGE_NEW);


        for(Prospect prospect : prospectList) {
            MailDto mailDto = MailDto
                    .builder()
                    .prospect(prospect)
                    .receiver(prospect.getEmail())
                    .subject(getEmailSubjectFreelancers())
                    .text(getEmailTextFreelancers())
                    .build();
            mailDtoList.add(mailDto);
        }

        if (!mailDtoList.isEmpty()) {
            List<Email> emailList = mailSender.send(mailDtoList, false);
            emailRepository.saveAll(emailList);
        }

        return mailDtoList;
    }

    public List<MailDto> sendEmailForTesting() throws MailjetSocketTimeoutException, MailjetException {
        List<MailDto> mailDtoList = new ArrayList<>();

        String tempEmail = "lorenzo.orlando724@gmail.com";

        MailDto mailDto = MailDto
                .builder()
                .prospect(null)
                .receiver(tempEmail)
                .subject(getEmailSubjectFreelancers())
                .text(getEmailTextFreelancers())
                .build();
        mailDtoList.add(mailDto);
        List<Email> emailList = mailSender.send(mailDtoList, true);

        return mailDtoList;
    }

    private String getEmailSubjectFreelancers() {
        return "Find Your Next Client with Our Instagram Contact Scraper";
    }

    private String getEmailSubjectBusiness() {
        return "Boost Your Business with Our Instagram Contact Scraper";
    }

    private String getEmailTextFreelancers() {
        return "Hello there,\n" +
                "\n" +
                "As a freelancer, finding new clients can be time-consuming and overwhelming. Our Instagram Contact Scraper tool can help streamline your search by providing you with a huge list of contacts, including their username, full name, biography, email, and phone number (if available).\n" +
                "\n" +
                "With this tool, you can easily target specific industries or demographics and expand your reach on the platform. Whether you're a graphic designer, writer, or social media manager, our tool can help you find new clients and grow your business.\n" +
                "\n" +
                "Some benefits of our tool include:\n" +
                "\n" +
                "- Quickly and easily build a targeted list of potential clients\n" +
                "- Save time and effort by automating the process of finding contacts\n" +
                "- Get access to contact information, such as email and phone numbers, that can be used for direct outreach\n" +
                "\n" +
                "Don't let manual searching hold you back from finding your next client. Try our Instagram Contact Scraper today and start seeing results.\n" +
                "\n" +
                "Best,\n" +
                "\n" +
                "Lorenzo";
    }

    private String getEmailTextBusinesses() {
        return "Hello there,\n" +
                "\n" +
                "Are you tired of manually searching for potential customers on Instagram? Our Instagram Contact Scraper tool can save you time and effort by providing you with a huge list of contacts, including their username, full name, biography, email, and phone number (if available).\n" +
                "\n" +
                "With this tool, you can easily target specific demographics and expand your reach on the platform. Whether you're a small business owner, marketer, or influencer, our tool can help you grow your online presence and drive more sales.\n" +
                "\n" +
                "Some benefits of our tool include:\n" +
                "\n" +
                "- Quickly and easily build a targeted list of potential customers\n" +
                "- Save time and effort by automating the process of finding contacts\n" +
                "- Get access to contact information, such as email and phone numbers, that can be used for direct marketing campaigns.\n" +
                "\n" +
                "Don't let manual searching hold you back from reaching your business goals on Instagram. Try our Instagram Contact Scraper today and start seeing results.\n" +
                "\n" +
                "Best,\n" +
                "Lorenzo";
    }
}
