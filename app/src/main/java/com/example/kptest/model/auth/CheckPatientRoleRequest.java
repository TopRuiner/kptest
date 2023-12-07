package com.example.kptest.model.auth;

public class CheckPatientRoleRequest {
    String email;

    public CheckPatientRoleRequest() {
    }

    //todo Email по токену можно
    public CheckPatientRoleRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
