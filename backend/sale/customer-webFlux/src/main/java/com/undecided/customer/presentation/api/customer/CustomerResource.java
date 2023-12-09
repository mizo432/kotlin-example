package com.undecided.customer.presentation.api.customer;

import com.undecided.customer.model.customer.Customer;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerResource {

    private final Map<Long, Customer> customers = new HashMap<>();

    @GetMapping("/{customerId}")
    public Mono<Customer> get(@PathVariable Long customerId) {
        return Mono.just(customers.get(customerId));

    }

    @GetMapping
    public Flux<Customer> getAll() {
        return Flux.just(customers.values().toArray(new Customer[]{}));

    }

    @PostMapping
    public Mono<Customer> post(@RequestBody Customer customer) {
        customers.put(customer.getCustomerId(), customer);
        return Mono.just(customer);

    }

    @PostMapping("/{customerId}")
    public Mono<Customer> put(@PathVariable Long customerId, @RequestBody Customer customer) {
        Customer oldCustomer = customers.get(customerId);
        customers.put(customerId, customer);
        return Mono.just(customer);

    }

    @DeleteMapping("/{customerId}")
    public Mono<Void> delete(@PathVariable Long customerId) {
        Customer oldCustomer = customers.get(customerId);
        customers.remove(customerId);
        return Mono.empty();

    }

}
