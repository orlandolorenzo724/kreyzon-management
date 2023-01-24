package com.oldigitalsolutions.management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "emails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prospect_id")
    private Prospect prospect;

    @Column(name = "data", nullable = false, length = Integer.MAX_VALUE)
    private String data;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;
}