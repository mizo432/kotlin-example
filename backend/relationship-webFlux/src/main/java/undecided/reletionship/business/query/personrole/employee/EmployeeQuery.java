package undecided.reletionship.business.query.personrole.employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeQuery {
    Flux<EmployeeDto> findAll();

    Mono<EmployeeDto> findOneByEmployeeNo(String employeeNo);
}
