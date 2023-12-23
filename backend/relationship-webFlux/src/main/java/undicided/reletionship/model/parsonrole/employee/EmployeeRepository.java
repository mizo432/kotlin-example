package undicided.reletionship.model.parsonrole.employee;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends R2dbcRepository<Employee, Long> {
    Mono<Employee> findByEmployeeNo(String employeeNo);
    
}
