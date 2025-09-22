package com.example.securitydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
package com.example.securitydemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class DataController {

    // Simula almacenamiento mínimo con consentimientos para Habeas Data
    private final Map<String, Boolean> consentStore = new ConcurrentHashMap<>();
    private final Map<String, String> userData = new ConcurrentHashMap<>();

    @PostMapping("/consent/{userId}")
    public ResponseEntity<?> giveConsent(@PathVariable String userId, @RequestBody Map<String,String> body) {
        consentStore.put(userId, true);
        userData.put(userId, body.getOrDefault("data", ""));
        return ResponseEntity.ok(Map.of("user", userId, "consent", true));
    }

    @DeleteMapping("/data/{userId}")
    public ResponseEntity<?> deleteData(@PathVariable String userId) {
        // Permite al usuario solicitar eliminación (Habeas Data)
        userData.remove(userId);
        consentStore.remove(userId);
        return ResponseEntity.ok(Map.of("user", userId, "deleted", true));
    }

    @GetMapping("/data/{userId}")
    public ResponseEntity<?> getData(@PathVariable String userId) {
        boolean consent = consentStore.getOrDefault(userId, false);
        if (!consent) {
            return ResponseEntity.status(403).body(Map.of("error", "no_consent"));
        }
        return ResponseEntity.ok(Map.of("user", userId, "data", userData.getOrDefault(userId, "")));
    }
}
