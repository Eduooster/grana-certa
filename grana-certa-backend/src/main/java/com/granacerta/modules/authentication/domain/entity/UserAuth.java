package com.granacerta.modules.authentication.domain.entity;

import com.granacerta.modules.user.domain.entity.UserRole;

public class UserAuth {

    private Long userId;
    private String email;
    private UserRole role;


    private UserCredential credential;

    public UserAuth() {
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }


    public UserCredential getCredential() {
        return credential;
    }
}
