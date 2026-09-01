package com.petcare.user_service.dto;

import com.petcare.user_service.entity.Role;
import com.petcare.user_service.entity.UserStatus;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private UserStatus status;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String email,
                        String phone, Role role, UserStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }
}