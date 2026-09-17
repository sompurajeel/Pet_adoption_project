package com.petadoption.shelter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petadoption.shelter.entity.Shelter;
import com.petadoption.shelter.repository.ShelterRepository;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository repository;

    public Shelter saveShelter(Shelter shelter) {

        if (shelter.getApprovalStatus() == null ||
                shelter.getApprovalStatus().isBlank()) {

            shelter.setApprovalStatus("PENDING");
        }

        return repository.save(shelter);
    }

    public List<Shelter> getAllShelters() {
        return repository.findAll();
    }

    public Optional<Shelter> getShelterById(Long id) {
        return repository.findById(id);
    }

    public Shelter updateShelter(Long id, Shelter shelter) {

        Shelter existingShelter = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelter not found"));

        existingShelter.setName(shelter.getName());
        existingShelter.setEmail(shelter.getEmail());
        existingShelter.setPhone(shelter.getPhone());
        existingShelter.setAddress(shelter.getAddress());
        existingShelter.setDescription(shelter.getDescription());

        return repository.save(existingShelter);
    }

    public Shelter approveShelter(Long id) {

        Shelter shelter = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelter not found"));

        shelter.setApprovalStatus("APPROVED");

        return repository.save(shelter);
    }

    public Shelter rejectShelter(Long id) {

        Shelter shelter = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelter not found"));

        shelter.setApprovalStatus("REJECTED");

        return repository.save(shelter);
    }

    public void deleteShelter(Long id) {
        repository.deleteById(id);
    }
}