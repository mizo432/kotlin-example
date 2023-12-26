package undecided.reletionship.business.command.personrole.employee;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import undecided.reletionship.model.parsonrole.employee.Employee;

@Service
public class EmployeeCommand {

    @Observed
    public Mono<Void> delete(String employeeNo) {
        return Mono.empty();

    }

    @Observed
    public Mono<Employee> update(String employeeNo, Employee entity) {
        return Mono.empty();

    }

    @Observed
    public Mono<Employee> insert(Employee employee) {
        return Mono.empty();

    }
}
