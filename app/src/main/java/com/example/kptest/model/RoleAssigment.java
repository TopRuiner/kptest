package com.example.kptest.model;



public class RoleAssigment {
//    @OneToOne
//    private PolyclinicUser polyclinicUser;
//    @OneToOne
//    private Role role;
    private long userId;
    private long roleId;

    public RoleAssigment() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
