package com.example.kptest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PolyclinicUser{

    private long id;

    private String email;

    private String password;


    private Boolean active;
    private Set<Role> roles;

    public PolyclinicUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


//    public PolyclinicUser(String email, String password, Collection<GrantedAuthority> mapRolesToAuthorities) {
//    }
}
