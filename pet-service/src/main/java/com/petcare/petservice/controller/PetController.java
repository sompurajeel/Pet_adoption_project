package com.petcare.petservice.controller;

import com.petcare.petservice.dto.PetRequest;
import com.petcare.petservice.dto.PetResponse;
import com.petcare.petservice.entity.PetStatus;
import com.petcare.petservice.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetResponse> createPet(@Valid @RequestBody PetRequest request) {
        return new ResponseEntity<>(petService.createPet(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> getPet(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PetResponse>> searchPets(
            @RequestParam(required = false) String species,
            @RequestParam(required = false) PetStatus status) {
        return ResponseEntity.ok(petService.searchPets(species, status));
    }

    @GetMapping("/shelter/{shelterId}")
    public ResponseEntity<List<PetResponse>> getPetsByShelter(@PathVariable Long shelterId) {
        return ResponseEntity.ok(petService.getPetsByShelter(shelterId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> updatePet(@PathVariable Long id, @Valid @RequestBody PetRequest request) {
        return ResponseEntity.ok(petService.updatePet(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PetResponse> updateStatus(@PathVariable Long id, @RequestParam PetStatus status) {
        return ResponseEntity.ok(petService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
