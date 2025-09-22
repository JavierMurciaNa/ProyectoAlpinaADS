package com.alpina.sgdv.controller;
import com.alpina.sgdv.domain.Customer; import com.alpina.sgdv.repo.InMemoryDB;
import org.springframework.web.bind.annotation.*; import java.util.*;

@RestController @RequestMapping("/customers")
public class CustomerController {
  private final InMemoryDB db; public CustomerController(InMemoryDB db){ this.db=db; }

  @GetMapping public Collection<Customer> all(){ return db.customers.values(); }
  @PostMapping public Customer create(@RequestBody Customer c){ if(c.getId()==null) c.setId(UUID.randomUUID().toString()); db.customers.put(c.getId(),c); return c; }
  @PutMapping("/{id}") public Customer update(@PathVariable String id, @RequestBody Customer c){ c.setId(id); db.customers.put(id,c); return c; }
  @DeleteMapping("/{id}") public void delete(@PathVariable String id){ db.customers.remove(id); }
}
