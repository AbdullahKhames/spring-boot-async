package tech.techiocean.async_consumer_1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import tech.techiocean.async_consumer_1.models.Customer;
import tech.techiocean.async_consumer_1.service.CustomerAsyncService;
import tech.techiocean.async_consumer_1.service.CustomerService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerAsyncService customerAsyncService;
    private final CustomerService customerService;

    @PostMapping("sync")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
    @GetMapping("sync")
    public ResponseEntity<List<Customer>> getCustomers() {
        return customerService.getCustomers();
    }
    @GetMapping("/sync/{customerID}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customerID) {
        return customerService.getCustomer(customerID);
    }

    @PostMapping("async")
    @Async
    public CompletableFuture<Customer> addCustomerAsync(@RequestBody Customer customer) {
        return customerAsyncService.addCustomerAsync(customer);
    }
    @GetMapping("async")
    @Async
    public CompletableFuture<ResponseEntity<List<Customer>>> getCustomersAsync() {
        return customerAsyncService.getCustomersAsync();
    }
    @GetMapping("/async/{customerID}")
    @Async
    public CompletableFuture<Customer> getCustomerAsync(@PathVariable int customerID) {
        return customerAsyncService.getCustomerAsync(customerID);
    }
}
