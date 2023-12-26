package undecided.reletionship.infra.query.personrole.employee;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.reletionship.business.query.personrole.employee.EmployeeDto;
import undecided.reletionship.business.query.personrole.employee.EmployeeQuery;
import undecided.reletionship.model.parsonrole.employee.Employee;
import undecided.reletionship.model.parsonrole.employee.EmployeeRepository;
import undecided.reletionship.model.party.person.PersonRepository;

import java.util.function.Function;

@Service
public class EmployeeQueryImpl implements EmployeeQuery {
    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;

    public EmployeeQueryImpl(EmployeeRepository employeeRepository, PersonRepository personRepository) {
        this.employeeRepository = employeeRepository;
        this.personRepository = personRepository;
    }

    @Override
    @Observed
    public Flux<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .flatMap((Function<Employee, Mono<EmployeeDto>>) employee -> personRepository.findById(employee.getEmployeeId())
                        .map(person -> EmployeeDto.reconstruct(employee, person)));

    }

    @Override
    @Observed
    public Mono<EmployeeDto> findOneByEmployeeNo(String employeeNo) {
        return employeeRepository.findByEmployeeNo(employeeNo)
                .flatMap((Function<Employee, Mono<EmployeeDto>>) employee ->
                        personRepository.findById(employee.getEmployeeId())
                                .map(person -> EmployeeDto.reconstruct(employee, person)));
    }

}
