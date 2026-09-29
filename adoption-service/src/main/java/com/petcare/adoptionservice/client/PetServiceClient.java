
package com.petcare.adoptionservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * REST client used by adoption-service to communicate with pet-service.
 *
 * Pet status rules:
 * - When adoption request is created: pet remains AVAILABLE.
 * - When adoption is approved: pet becomes ADOPTED.
 * - When adoption is rejected/cancelled: pet becomes AVAILABLE.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PetServiceClient {

    private final RestTemplate restTemplate;

    @Value("${services.pet-service.base-url}")
    private String petServiceBaseUrl;

    /**
     * Mark pet as adopted after an adoption request is approved.
     */
    public void markPetAsAdopted(Long petId) {
        updatePetStatus(petId, "ADOPTED");
    }

    /**
     * When an adoption request is created, the pet remains AVAILABLE.
     *
     * Pet Service does not have a PENDING_ADOPTION status,
     * so no status update is sent here.
     */
    public void markPetAsPendingAdoption(Long petId) {
        log.info(
                "Adoption request created for petId={}; pet remains AVAILABLE",
                petId
        );
    }

    /**
     * Mark pet as available after an adoption request is rejected
     * or cancelled.
     */
    public void markPetAsAvailable(Long petId) {
        updatePetStatus(petId, "AVAILABLE");
    }

    /**
     * Send pet status update to Pet Service.
     */
    private void updatePetStatus(Long petId, String status) {
        try {
            String url = petServiceBaseUrl
                    + "/api/pets/"
                    + petId
                    + "/status?status="
                    + status;

            restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.PATCH,
                    null,
                    Void.class
            );

            log.info(
                    "Pet status updated successfully: petId={}, status={}",
                    petId,
                    status
            );

        } catch (Exception ex) {
            // In a production system this would publish to a retry queue / DLQ.
            log.warn(
                    "Failed to sync pet status for petId={} to status={}: {}",
                    petId,
                    status,
                    ex.getMessage()
            );
        }
    }
}
