package undicided.reletionship.business.command.partyrole.employee;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import undicided.reletionship.business.query.partyrole.employee.EmployeeDto;
import undicided.reletionship.model.partyrole.employee.Employee;

@Service
public class EmployeeCommand {

    public Mono<Void> delete(String employeeCode) {
        return Mono.empty();

    }

    public Mono<EmployeeDto> save(String employeeCode, Employee entity) {
        return Mono.empty();

    }

    public Mono<EmployeeDto> post(EmployeeDto employeeDto) {
        return Mono.empty();
    }
}
