package com.petadoption.shelter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petadoption.shelter.entity.Shelter;
import com.petadoption.shelter.service.ShelterService;

@RestController
@RequestMapping("/shelters")
@CrossOrigin(origins = "*")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @PostMapping
    public ResponseEntity<Shelter> createShelter(
            @RequestBody Shelter shelter) {

        return ResponseEntity.ok(
                shelterService.saveShelter(shelter)
        );
    }

    @GetMapping
    public ResponseEntity<List<Shelter>> getAllShelters() {

        return ResponseEntity.ok(
                shelterService.getAllShelters()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(
            @PathVariable Long id) {

        Optional<Shelter> shelter =
                shelterService.getShelterById(id);

        if (shelter.isPresent()) {
            return ResponseEntity.ok(shelter.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shelter> updateShelter(
            @PathVariable Long id,
            @RequestBody Shelter shelter) {

        try {
            return ResponseEntity.ok(
                    shelterService.updateShelter(id, shelter)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Shelter> approveShelter(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    shelterService.approveShelter(id)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Shelter> rejectShelter(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    shelterService.rejectShelter(id)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelter(
            @PathVariable Long id) {

        shelterService.deleteShelter(id);

        return ResponseEntity.ok(
                "Shelter deleted successfully"
        );
    }
}