package com.oldigitalsolutions.management.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCriteria {
    private String key;
    private String operation;
    private String value;
    private LocalDateTime valueLocalDateTime;

    public SearchCriteria(String key, String operation, String value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, String operation, LocalDateTime valueLocalDateTime) {
        this.key = key;
        this.operation = operation;
        this.valueLocalDateTime = valueLocalDateTime;
    }
}