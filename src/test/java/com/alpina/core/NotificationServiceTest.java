package com.alpina.core;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.Test;

class NotificationServiceTest {

    private final Clock fixedClock =
            Clock.fixed(Instant.parse("2025-01-01T12:00:00Z"), ZoneOffset.UTC);

    @Test
    void generaNotificacionCuandoSeCreaPedido() {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.registerCustomer(
                "Carlos", "carlos@example.com", "3000000000");

        NotificationService notificationService =
                new NotificationService(customerService, fixedClock);

        Notification notification = notificationService.sendOrderNotification(
                customer.getId(),
                "PED-123",
                NotificationType.ORDER_CREATED
        );

        assertEquals(customer.getId(), notification.getCustomerId());
        assertEquals("PED-123", notification.getOrderId());
        assertEquals(NotificationType.ORDER_CREATED, notification.getType());
        assertTrue(notification.getMessage().contains("PED-123"));

        List<Notification> list =
                notificationService.getNotificationsForCustomer(customer.getId());
        assertEquals(1, list.size());
    }

    @Test
    void noPermiteNotificarClienteInactivo() {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.registerCustomer(
                "Laura", "laura@example.com", "3110000000");
        customerService.deactivateCustomer(customer.getId());

        NotificationService notificationService =
                new NotificationService(customerService, fixedClock);

        assertThrows(IllegalArgumentException.class,
                () -> notificationService.sendOrderNotification(
                        customer.getId(),
                        "PED-999",
                        NotificationType.ORDER_CREATED
                ));
    }
}
