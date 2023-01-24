package com.oldigitalsolutions.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "setup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Setup {
    @Id
    @Column(name = "prospect_data_file_path")
    private String prospectDataFilePath;

    private String apiKey;

    private String apiSecret;

    private String emailFrom;

    private String nameFrom;
}