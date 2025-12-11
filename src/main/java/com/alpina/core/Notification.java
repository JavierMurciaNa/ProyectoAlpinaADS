package com.alpina.core;

import java.time.Instant;
import java.util.Objects;

/**
 * Representa una notificación enviada a un cliente
 * sobre el estado de un pedido.
 */
public class Notification {

    private final String customerId;
    private final String orderId;
    private final NotificationType type;
    private final String message;
    private final Instant createdAt;

    public Notification(String customerId,
                        String orderId,
                        NotificationType type,
                        String message,
                        Instant createdAt) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customerId no puede ser vacío");
        }
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId no puede ser vacío");
        }
        this.customerId = customerId;
        this.orderId = orderId;
        this.type = Objects.requireNonNull(type, "type no puede ser nulo");
        this.message = Objects.requireNonNull(message, "message no puede ser nulo");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt no puede ser nulo");
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public NotificationType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
