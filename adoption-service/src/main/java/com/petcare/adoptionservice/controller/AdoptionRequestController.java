package com.petcare.adoptionservice.controller;

import com.petcare.adoptionservice.dto.AdoptionRequestDto;
import com.petcare.adoptionservice.dto.AdoptionResponseDto;
import com.petcare.adoptionservice.dto.ReviewRequestDto;
import com.petcare.adoptionservice.service.AdoptionRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoption-requests")
@RequiredArgsConstructor
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionRequestService;

    @PostMapping
    public ResponseEntity<AdoptionResponseDto> createRequest(@Valid @RequestBody AdoptionRequestDto dto) {
        return new ResponseEntity<>(adoptionRequestService.createRequest(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionResponseDto> getRequest(@PathVariable Long id) {
        return ResponseEntity.ok(adoptionRequestService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AdoptionResponseDto>> getAllRequests() {
        return ResponseEntity.ok(adoptionRequestService.getAll());
    }

    @GetMapping("/adopter/{adopterId}")
    public ResponseEntity<List<AdoptionResponseDto>> getByAdopter(@PathVariable Long adopterId) {
        return ResponseEntity.ok(adoptionRequestService.getByAdopter(adopterId));
    }

    @GetMapping("/shelter/{shelterId}")
    public ResponseEntity<List<AdoptionResponseDto>> getByShelter(@PathVariable Long shelterId) {
        return ResponseEntity.ok(adoptionRequestService.getByShelter(shelterId));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<AdoptionResponseDto> approve(@PathVariable Long id,
                                                         @RequestBody(required = false) ReviewRequestDto review) {
        String note = review != null ? review.getReviewNote() : null;
        return ResponseEntity.ok(adoptionRequestService.approve(id, note));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<AdoptionResponseDto> reject(@PathVariable Long id,
                                                        @RequestBody(required = false) ReviewRequestDto review) {
        String note = review != null ? review.getReviewNote() : null;
        return ResponseEntity.ok(adoptionRequestService.reject(id, note));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<AdoptionResponseDto> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(adoptionRequestService.cancel(id));
    }
}
