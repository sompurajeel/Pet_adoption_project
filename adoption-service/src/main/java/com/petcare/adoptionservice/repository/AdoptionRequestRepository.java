package com.petcare.adoptionservice.repository;

import com.petcare.adoptionservice.entity.AdoptionRequest;
import com.petcare.adoptionservice.entity.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {

    List<AdoptionRequest> findByAdopterId(Long adopterId);

    List<AdoptionRequest> findByShelterId(Long shelterId);

    List<AdoptionRequest> findByPetId(Long petId);

    List<AdoptionRequest> findByStatus(AdoptionStatus status);

    boolean existsByPetIdAndAdopterIdAndStatus(Long petId, Long adopterId, AdoptionStatus status);
}
