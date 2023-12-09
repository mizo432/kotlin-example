package undecided.employee.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeQuery {
    Mono<EmployeeWithDepartment> findOneBy(Long id);

    Flux<EmployeeWithDepartment> findEmployeesWithDepartment();
}
