package tech.techiocean.async_resource_1.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.techiocean.async_resource_1.models.Customer;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
    private static final List<Customer> customers = new ArrayList<>();
    @PostConstruct
    public void init() {
        customers.add(new Customer(1, "Alice", "alice@gmail.com"));
        customers.add(new Customer(2, "Bob", "bob@gmail.com"));
    }
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        log.info("Adding customer: {}", customer);
        Integer id = customers.getLast().id();
        customer = new Customer(id + 1, customer.name(), customer.email());
        customers.add(customer);
        return ResponseEntity.ok(customer);
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        log.info("Getting all customers");
        log.info("Customers: {}", customers);
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/{customerID}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customerID) {
        log.info("Getting customer with ID: {}", customerID);
        return ResponseEntity.ok(customers.get(customerID));
    }
}
