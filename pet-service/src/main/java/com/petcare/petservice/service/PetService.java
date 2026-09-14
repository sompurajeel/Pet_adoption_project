package com.petcare.petservice.service;

import com.petcare.petservice.dto.PetRequest;
import com.petcare.petservice.dto.PetResponse;
import com.petcare.petservice.entity.Pet;
import com.petcare.petservice.entity.PetStatus;
import com.petcare.petservice.exception.ResourceNotFoundException;
import com.petcare.petservice.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PetService {

    private final PetRepository petRepository;

    public PetResponse createPet(PetRequest request) {
        Pet pet = Pet.builder()
                .name(request.getName())
                .species(request.getSpecies())
                .breed(request.getBreed())
                .ageInMonths(request.getAgeInMonths())
                .gender(request.getGender())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .shelterId(request.getShelterId())
                .status(PetStatus.AVAILABLE)
                .build();

        return toResponse(petRepository.save(pet));
    }

    @Transactional(readOnly = true)
    public PetResponse getPetById(Long id) {
        return toResponse(findPetOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<PetResponse> getAllPets() {
        return petRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<PetResponse> searchPets(String species, PetStatus status) {
        List<Pet> pets;
        if (species != null && status != null) {
            pets = petRepository.findBySpeciesIgnoreCaseAndStatus(species, status);
        } else if (status != null) {
            pets = petRepository.findByStatus(status);
        } else {
            pets = petRepository.findAll();
        }
        return pets.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<PetResponse> getPetsByShelter(Long shelterId) {
        return petRepository.findByShelterId(shelterId).stream().map(this::toResponse).toList();
    }

    public PetResponse updatePet(Long id, PetRequest request) {
        Pet pet = findPetOrThrow(id);
        pet.setName(request.getName());
        pet.setSpecies(request.getSpecies());
        pet.setBreed(request.getBreed());
        pet.setAgeInMonths(request.getAgeInMonths());
        pet.setGender(request.getGender());
        pet.setDescription(request.getDescription());
        pet.setImageUrl(request.getImageUrl());
        return toResponse(petRepository.save(pet));
    }

    public PetResponse updateStatus(Long id, PetStatus status) {
        Pet pet = findPetOrThrow(id);
        pet.setStatus(status);
        return toResponse(petRepository.save(pet));
    }

    public void deletePet(Long id) {
        Pet pet = findPetOrThrow(id);
        petRepository.delete(pet);
    }

    private Pet findPetOrThrow(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));
    }

    private PetResponse toResponse(Pet pet) {
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .species(pet.getSpecies())
                .breed(pet.getBreed())
                .ageInMonths(pet.getAgeInMonths())
                .gender(pet.getGender())
                .description(pet.getDescription())
                .imageUrl(pet.getImageUrl())
                .status(pet.getStatus())
                .shelterId(pet.getShelterId())
                .createdAt(pet.getCreatedAt())
                .updatedAt(pet.getUpdatedAt())
                .build();
    }
}
