package undecided.reletionship.infra.query.personrole.employee;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import undecided.reletionship.business.query.personrole.employee.EmployeeDto;
import undecided.reletionship.business.query.personrole.employee.EmployeeQuery;
import undecided.reletionship.model.parsonrole.employee.Employee;
import undecided.reletionship.model.parsonrole.employee.EmployeeRepository;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(employee -> {
                    Optional<Person> person = personRepository.findById(employee.getEmployeeId());
                    return EmployeeDto.reconstruct(employee, person.orElseThrow());
                }).collect(Collectors.toList());

    }

    @Override
    @Observed
    public EmployeeDto findOneByEmployeeNo(String employeeNo) {
        Employee employee = employeeRepository.findByEmployeeNo(employeeNo);
        Optional<Person> personOptional = personRepository.findById(employee.getEmployeeId());
        return EmployeeDto.reconstruct(employee, personOptional.orElseThrow());

    }

}
