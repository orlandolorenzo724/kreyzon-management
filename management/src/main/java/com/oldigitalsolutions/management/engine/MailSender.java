package com.oldigitalsolutions.management.engine;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import com.oldigitalsolutions.management.model.Email;
import com.oldigitalsolutions.management.model.Prospect;
import com.oldigitalsolutions.management.repository.ProspectRepository;
import com.oldigitalsolutions.management.request.MailDto;
import com.oldigitalsolutions.management.utils.Constant;
import com.oldigitalsolutions.management.utils.IDGenerator;
import com.oldigitalsolutions.management.utils.StageUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MailSender {
    @Autowired
    private IDGenerator idGenerator;

    @Autowired
    private ProspectRepository prospectRepository;

    public List<com.oldigitalsolutions.management.model.Email> send(List<MailDto> mailDtoList, Boolean testing) throws MailjetSocketTimeoutException, MailjetException {
        List<Email> emailList = new ArrayList<>();

        MailjetClient client = new MailjetClient("488a15fd8f09348d32cd0e8093f9840e", "4cae583a4d543112c344e267172b3e84", new ClientOptions("v3.1"));
        MailjetResponse response;

        int emailIdCounter = 0;

        for (int i = 0; i < mailDtoList.size(); i++) {
            MailDto mailDto = mailDtoList.get(i);
            if (mailDto != null) {
                MailjetRequest request = new MailjetRequest(Emailv31.resource)
                        .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                        .put(Emailv31.Message.FROM, new JSONObject()
                        .put("Email", "orlandolorenzomk@gmail.com")
                        .put("Name", "Lorenzo Orlando"))
                        .put(Emailv31.Message.TO, new JSONArray()
                        .put(new JSONObject()
                        .put("Email", mailDto.receiver())
                        .put("Name", "Lorenzo Orlando")))
                        .put(Emailv31.Message.SUBJECT, mailDto.subject())
                        // .put(Emailv31.Message.TEXTPART, mailRequest.getText())
                        //.put(Emailv31.Message.HTMLPART, "<h3>" + mailRequest.getText() + "</h3>")
                        .put(Emailv31.Message.TEXTPART, mailDto.text())
                        .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
                response = client.post(request);
                if (response.getStatus() == 200) {
                    Long id = -1L;
                    if (emailIdCounter == 0)
                        id = idGenerator.generateEmailId();
                    else
                        id += 1;

                    if (!testing) {
                        com.oldigitalsolutions.management.model.Email email = com.oldigitalsolutions.management.model.Email
                                .builder()
                                .id(id)
                                .prospect(mailDto.prospect())
                                .data(response.getData().toString())
                                .sentAt(LocalDateTime.now())
                                .build();
                        emailList.add(email);
                        Prospect prospect = mailDto.prospect();
                        prospect.setStage(StageUtils.getNextStage(prospect.getStage()));
                        prospectRepository.save(prospect);
                    }
                }

                log.info("Sending email to " + mailDto.receiver());
                log.info(String.valueOf(response.getStatus()));
                log.info(String.valueOf(response.getData()));
                log.info("Email sent");
            }
        }

        return emailList;
    }
}