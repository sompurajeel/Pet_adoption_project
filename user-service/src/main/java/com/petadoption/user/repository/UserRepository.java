package com.petadoption.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petadoption.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}