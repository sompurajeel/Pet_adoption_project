package com.petcare.petservice.dto;

import com.petcare.petservice.entity.PetStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private Integer ageInMonths;
    private String gender;
    private String description;
    private String imageUrl;
    private PetStatus status;
    private Long shelterId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
