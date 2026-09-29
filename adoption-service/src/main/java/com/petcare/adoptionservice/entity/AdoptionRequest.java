package com.petcare.adoptionservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long petId;

    @Column(nullable = false)
    private Long adopterId;

    @Column(nullable = false)
    private Long shelterId;

    @Column(length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AdoptionStatus status = AdoptionStatus.PENDING;

    @Column(length = 1000)
    private String reviewNote;

    @Column(updatable = false)
    private LocalDateTime requestDate;

    private LocalDateTime decisionDate;

    @PrePersist
    protected void onCreate() {
        this.requestDate = LocalDateTime.now();
    }
}
