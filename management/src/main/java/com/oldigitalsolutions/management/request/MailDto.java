package com.oldigitalsolutions.management.request;

import com.oldigitalsolutions.management.model.Prospect;
import lombok.Builder;

@Builder
public record MailDto(Prospect prospect, String receiver, String subject, String text) {
}
