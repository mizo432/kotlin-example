package com.undecided.employee.service;

import reactor.core.publisher.Mono;

public interface EmployeeQuery {
    Mono<EmployeeAssy> findOneBy(Long id);

}
