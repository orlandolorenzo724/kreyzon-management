package com.oldigitalsolutions.management.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "prospects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Prospect {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "company", nullable = false, length = Integer.MAX_VALUE)
    private String company;

    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "phone", length = Integer.MAX_VALUE)
    private String phone;

    @Column(name = "stage", nullable = false, length = Integer.MAX_VALUE)
    private String stage;

    @Column(name = "category")
    private String category;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

    @Column(name = "contactable")
    private String contactable;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Transient
    private List<SocialAccount> socialAccount;
}