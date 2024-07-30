package tech.techiocean.async_consumer_1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tech.techiocean.async_consumer_1.models.Customer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerAsyncService {
    private final RestTemplate restTemplate;
    @Async
    public CompletableFuture<Customer> addCustomerAsync(@RequestBody Customer customer) {
        CompletableFuture<Customer> future = CompletableFuture.supplyAsync(() -> {
            log.info("thread: {}", Thread.currentThread().getName());

            log.info("Adding customer: {}", customer);

            return restTemplate.postForObject("http://localhost:8080/customers", customer, Customer.class);
        });
        return future;
    }
    @Async
    public CompletableFuture<ResponseEntity<List<Customer>>> getCustomersAsync() {
        CompletableFuture<ResponseEntity<List<Customer>>> future = CompletableFuture.supplyAsync(() -> {
            log.info("thread: {}", Thread.currentThread().getName());
            log.info("Getting all customers");
            return restTemplate.exchange("http://localhost:8080/customers", HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {
            });
        });
        return future;
    }
    @Async
    public CompletableFuture<Customer> getCustomerAsync(@PathVariable int customerID) {
        CompletableFuture<Customer> future = CompletableFuture.supplyAsync(() -> {
            log.info("thread: {}", Thread.currentThread().getName());

            log.info("Getting customer with ID: {}", customerID);
            return restTemplate.getForObject("http://localhost:8080/customers/{customerID}", Customer.class, customerID);
        });
        return future;
    }

}
