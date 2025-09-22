package com.example.support.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final Map<String, List<String>> history = new HashMap<>();
    private final List<String> agents = Arrays.asList("Agente-1", "Agente-2", "Agente-3");
    private final AtomicInteger roundRobin = new AtomicInteger(0);

    @PostMapping("/{clientId}")
    public Map<String, String> sendMessage(@PathVariable String clientId, @RequestBody Map<String, String> body) {
        String msg = body.getOrDefault("message", "");
        history.putIfAbsent(clientId, new ArrayList<>());
        history.get(clientId).add("Cliente: " + msg);

        // Simulación: asignar agente de soporte en orden
        String agent = agents.get(roundRobin.getAndIncrement() % agents.size());
        String response = "[EN PROGRESS] Respuesta automática de " + agent;

        history.get(clientId).add(agent + ": " + response);

        return Map.of("agent", agent, "response", response);
    }

    @GetMapping("/{clientId}/history")
    public List<String> getHistory(@PathVariable String clientId) {
        return history.getOrDefault(clientId, List.of("[EN PROGRESS] Sin historial"));
    }
}
