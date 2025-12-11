package com.alpina.core;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa un cliente de Alpina dentro del sistema.
 */
public class Customer {

    private final String id;
    private String name;
    private String email;
    private String phone;
    private boolean active;

    public Customer(String name, String email, String phone) {
        this(UUID.randomUUID().toString(), name, email, phone, true);
    }

    public Customer(String id, String name, String email, String phone, boolean active) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id del cliente no puede ser vacío");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email no puede ser vacío");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.active = active;
    }

    public String getId() {
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

    public boolean isActive() {
        return active;
    }

    public void updateContactInfo(String newEmail, String newPhone) {
        if (newEmail == null || newEmail.isBlank()) {
            throw new IllegalArgumentException("El email no puede ser vacío");
        }
        this.email = newEmail;
        this.phone = newPhone;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
