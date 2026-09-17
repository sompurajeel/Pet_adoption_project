
package com.petadoption.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petadoption.shelter.entity.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {

}