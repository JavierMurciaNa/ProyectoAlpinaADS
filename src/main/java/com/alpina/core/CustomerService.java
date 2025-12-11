package com.alpina.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio de dominio para la gestión de clientes.
 * Simula un repositorio en memoria (no BD real).
 */
public class CustomerService {

    private final Map<String, Customer> customers = new HashMap<>();

    public Customer registerCustomer(String name, String email, String phone) {
        Customer customer = new Customer(name, email, phone);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customers.get(id));
    }

    public List<Customer> listActiveCustomers() {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customers.values()) {
            if (c.isActive()) {
                result.add(c);
            }
        }
        return result;
    }

    public void deactivateCustomer(String id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new IllegalArgumentException("Cliente no encontrado: " + id);
        }
        customer.deactivate();
    }
}
