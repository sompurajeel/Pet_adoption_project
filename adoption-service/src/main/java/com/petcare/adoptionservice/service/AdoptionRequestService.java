package com.petcare.adoptionservice.service;

import com.petcare.adoptionservice.client.PetServiceClient;
import com.petcare.adoptionservice.dto.AdoptionRequestDto;
import com.petcare.adoptionservice.dto.AdoptionResponseDto;
import com.petcare.adoptionservice.entity.AdoptionRequest;
import com.petcare.adoptionservice.entity.AdoptionStatus;
import com.petcare.adoptionservice.exception.InvalidRequestException;
import com.petcare.adoptionservice.exception.ResourceNotFoundException;
import com.petcare.adoptionservice.repository.AdoptionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdoptionRequestService {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final PetServiceClient petServiceClient;

    public AdoptionResponseDto createRequest(AdoptionRequestDto dto) {
        boolean alreadyPending = adoptionRequestRepository
                .existsByPetIdAndAdopterIdAndStatus(dto.getPetId(), dto.getAdopterId(), AdoptionStatus.PENDING);

        if (alreadyPending) {
            throw new InvalidRequestException(
                    "An active adoption request already exists for this pet and adopter");
        }

        AdoptionRequest request = AdoptionRequest.builder()
                .petId(dto.getPetId())
                .adopterId(dto.getAdopterId())
                .shelterId(dto.getShelterId())
                .message(dto.getMessage())
                .status(AdoptionStatus.PENDING)
                .build();

        AdoptionRequest saved = adoptionRequestRepository.save(request);
        petServiceClient.markPetAsPendingAdoption(dto.getPetId());
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public AdoptionResponseDto getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<AdoptionResponseDto> getAll() {
        return adoptionRequestRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<AdoptionResponseDto> getByAdopter(Long adopterId) {
        return adoptionRequestRepository.findByAdopterId(adopterId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<AdoptionResponseDto> getByShelter(Long shelterId) {
        return adoptionRequestRepository.findByShelterId(shelterId).stream().map(this::toResponse).toList();
    }

    public AdoptionResponseDto approve(Long id, String reviewNote) {
        AdoptionRequest request = findOrThrow(id);
        ensurePending(request);

        request.setStatus(AdoptionStatus.APPROVED);
        request.setReviewNote(reviewNote);
        request.setDecisionDate(LocalDateTime.now());

        AdoptionRequest saved = adoptionRequestRepository.save(request);
        petServiceClient.markPetAsAdopted(request.getPetId());
        return toResponse(saved);
    }

    public AdoptionResponseDto reject(Long id, String reviewNote) {
        AdoptionRequest request = findOrThrow(id);
        ensurePending(request);

        request.setStatus(AdoptionStatus.REJECTED);
        request.setReviewNote(reviewNote);
        request.setDecisionDate(LocalDateTime.now());

        AdoptionRequest saved = adoptionRequestRepository.save(request);
        petServiceClient.markPetAsAvailable(request.getPetId());
        return toResponse(saved);
    }

    public AdoptionResponseDto cancel(Long id) {
        AdoptionRequest request = findOrThrow(id);
        ensurePending(request);

        request.setStatus(AdoptionStatus.CANCELLED);
        request.setDecisionDate(LocalDateTime.now());

        AdoptionRequest saved = adoptionRequestRepository.save(request);
        petServiceClient.markPetAsAvailable(request.getPetId());
        return toResponse(saved);
    }

    private void ensurePending(AdoptionRequest request) {
        if (request.getStatus() != AdoptionStatus.PENDING) {
            throw new InvalidRequestException(
                    "Adoption request " + request.getId() + " has already been finalized with status "
                            + request.getStatus());
        }
    }

    private AdoptionRequest findOrThrow(Long id) {
        return adoptionRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adoption request not found with id: " + id));
    }

    private AdoptionResponseDto toResponse(AdoptionRequest request) {
        return AdoptionResponseDto.builder()
                .id(request.getId())
                .petId(request.getPetId())
                .adopterId(request.getAdopterId())
                .shelterId(request.getShelterId())
                .message(request.getMessage())
                .status(request.getStatus())
                .reviewNote(request.getReviewNote())
                .requestDate(request.getRequestDate())
                .decisionDate(request.getDecisionDate())
                .build();
    }
}
