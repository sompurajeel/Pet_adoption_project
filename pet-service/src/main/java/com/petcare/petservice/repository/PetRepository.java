package com.petcare.petservice.repository;

import com.petcare.petservice.entity.Pet;
import com.petcare.petservice.entity.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long>, JpaSpecificationExecutor<Pet> {

    List<Pet> findByShelterId(Long shelterId);

    List<Pet> findByStatus(PetStatus status);

    List<Pet> findBySpeciesIgnoreCaseAndStatus(String species, PetStatus status);
}
