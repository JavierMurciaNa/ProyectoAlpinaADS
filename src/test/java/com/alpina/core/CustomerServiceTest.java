package com.alpina.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class CustomerServiceTest {

    private final CustomerService service = new CustomerService();

    @Test
    void registraClienteActivoPorDefecto() {
        Customer c = service.registerCustomer("Juan", "juan@example.com", "3010000000");

        assertNotNull(c.getId());
        assertEquals("Juan", c.getName());
        assertTrue(c.isActive());
        assertTrue(service.listActiveCustomers().contains(c));
    }

    @Test
    void desactivaClienteYNoApareceEnActivos() {
        Customer c = service.registerCustomer("Ana", "ana@example.com", "3020000000");

        service.deactivateCustomer(c.getId());

        assertFalse(c.isActive());
        List<Customer> activos = service.listActiveCustomers();
        assertFalse(activos.contains(c));
    }

    @Test
    void lanzarExcepcionAlDesactivarClienteInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> service.deactivateCustomer("no-existe"));
    }
}
