package com.undecided.employee.model.employee;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeRepository {
    Map<Long, Employee> map = new HashMap<>();

    public EmployeeRepository() {
        map.put(1L, Employee.create(1L, "name1", 1L));
        map.put(2L, Employee.create(2L, "name2", 1L));
        map.put(3L, Employee.create(3L, "name3", 1L));

    }

    public Flux<Employee> findAll() {
        return Flux.just(map.values().toArray(new Employee[]{}));
    }

    public Employee save(Employee employee) {
        map.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public Mono<Employee> findById(Long id) {
        return Mono.just(map.get(id));
    }

    public void deleteById(Long id) {
        map.remove(id);
    }
}
