package com.petcare.adoptionservice.dto;

import com.petcare.adoptionservice.entity.AdoptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionResponseDto {
    private Long id;
    private Long petId;
    private Long adopterId;
    private Long shelterId;
    private String message;
    private AdoptionStatus status;
    private String reviewNote;
    private LocalDateTime requestDate;
    private LocalDateTime decisionDate;
}
