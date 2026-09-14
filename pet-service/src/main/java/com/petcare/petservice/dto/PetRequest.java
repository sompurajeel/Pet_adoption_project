package com.petcare.petservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PetRequest {

    @NotBlank(message = "Pet name is required")
    private String name;

    @NotBlank(message = "Species is required")
    private String species;

    private String breed;

    private Integer ageInMonths;

    private String gender;

    private String description;

    private String imageUrl;

    @NotNull(message = "Shelter ID is required")
    private Long shelterId;
}
