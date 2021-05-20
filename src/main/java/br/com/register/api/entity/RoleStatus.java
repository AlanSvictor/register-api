package br.com.register.api.entity;

public enum RoleStatus {
    ACTIVATED("ACTIVATED"),
    DEACTIVATED("DEACTIVATED"),
    DELETED("DELETED");

    private final String status;

    RoleStatus(String status) {
        this.status = status;
    }
}