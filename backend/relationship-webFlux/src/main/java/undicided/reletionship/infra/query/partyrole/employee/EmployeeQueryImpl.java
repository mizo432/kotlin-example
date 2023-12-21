package undicided.reletionship.infra.query.partyrole.employee;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undicided.reletionship.business.query.partyrole.employee.EmployeeDto;
import undicided.reletionship.business.query.partyrole.employee.EmployeeQuery;

@Service
public class EmployeeQueryImpl implements EmployeeQuery {
    @Override
    public Flux<EmployeeDto> findAll() {
        return Flux.empty();
    }

    @Override
    public Mono<EmployeeDto> findOneBy(String employeeCode) {
        return Mono.empty();
    }
}
