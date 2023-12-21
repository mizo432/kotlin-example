package undicided.reletionship.business.query.partyrole.employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeQuery {
    Flux<EmployeeDto> findAll();

    Mono<EmployeeDto> findOneBy(String employeeCode);
}
