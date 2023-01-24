package com.oldigitalsolutions.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "temp_prospect")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempProspect {
    @Id
    private String email;

    private String username;

    private String category;

    private String contactNumber;

    private String externalUrl;

    private String fullName;
}