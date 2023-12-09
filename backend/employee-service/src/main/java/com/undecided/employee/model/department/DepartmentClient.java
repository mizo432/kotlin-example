package com.undecided.employee.model.department;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

@HttpExchange
public interface DepartmentClient {

    @GetExchange("/api/v1/departments/{id}")
    Mono<Department> findOneById(@PathVariable Long id);

}
