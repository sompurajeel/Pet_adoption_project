package com.petcare.adoptionservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdoptionRequestDto {

    @NotNull(message = "Pet ID is required")
    private Long petId;

    @NotNull(message = "Adopter ID is required")
    private Long adopterId;

    @NotNull(message = "Shelter ID is required")
    private Long shelterId;

    private String message;
}
