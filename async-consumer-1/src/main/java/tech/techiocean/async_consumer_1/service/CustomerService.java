package tech.techiocean.async_consumer_1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import tech.techiocean.async_consumer_1.models.Customer;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final RestTemplate restTemplate;

    public ResponseEntity<Customer> addCustomer(Customer customer) {
        log.info("Adding customer: {}", customer);
        return ResponseEntity.ok(restTemplate.postForObject("http://localhost:8080/customers", customer, Customer.class));
    }
    public ResponseEntity<List<Customer>> getCustomers() {
        log.info("Getting all customers");

        return ResponseEntity.ok(List.of(restTemplate.getForObject("http://localhost:8080/customers", Customer[].class)));
    }
    public ResponseEntity<Customer> getCustomer(int customerID) {
        log.info("Getting customer with ID: {}", customerID);
        return ResponseEntity.ok(restTemplate.getForObject("http://localhost:8080/customers/{customerID}", Customer.class, customerID));
    }
}
