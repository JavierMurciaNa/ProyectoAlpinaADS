package com.alpina.core;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que genera notificaciones de pedidos para los clientes.
 * Simula una cola de notificaciones en memoria.
 */
public class NotificationService {

    private final CustomerService customerService;
    private final Clock clock;
    private final List<Notification> sentNotifications = new ArrayList<>();

    public NotificationService(CustomerService customerService, Clock clock) {
        this.customerService = customerService;
        this.clock = clock;
    }

    public Notification sendOrderNotification(String customerId,
                                              String orderId,
                                              NotificationType type) {
        // Validar que el cliente existe y está activo
        Customer customer = customerService.findById(customerId)
                .filter(Customer::isActive)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se puede notificar un cliente inexistente o inactivo: " + customerId));

        String message = buildMessage(customer, orderId, type);
        Instant now = Instant.now(clock);

        Notification notification = new Notification(
                customer.getId(),
                orderId,
                type,
                message,
                now
        );

        sentNotifications.add(notification);
        return notification;
    }

    private String buildMessage(Customer customer, String orderId, NotificationType type) {
        switch (type) {
            case ORDER_CREATED:
                return "Hola " + customer.getName()
                        + ", recibimos tu pedido " + orderId
                        + ". Gracias por comprar en Alpina.";
            case ORDER_DISPATCHED:
                return "Tu pedido " + orderId + " ha sido despachado.";
            case ORDER_DELIVERED:
                return "Tu pedido " + orderId + " fue entregado. ¡Buen provecho!";
            default:
                throw new IllegalArgumentException("Tipo de notificación no soportado: " + type);
        }
    }

    public List<Notification> getNotificationsForCustomer(String customerId) {
        List<Notification> result = new ArrayList<>();
        for (Notification n : sentNotifications) {
            if (n.getCustomerId().equals(customerId)) {
                result.add(n);
            }
        }
        return result;
    }
}
