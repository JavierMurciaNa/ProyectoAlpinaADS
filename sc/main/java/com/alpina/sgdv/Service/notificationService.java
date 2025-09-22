package com.alpina.sgdv.service;
import org.springframework.stereotype.Service;
@Service
public class NotificationService {
  public void notifyCustomer(String email, String subject, String body){
    // Mock: imprime en logs → simula “Notificaciones automáticas”
    System.out.printf("[EMAIL] to=%s | %s | %s%n", email, subject, body);
  }
}
