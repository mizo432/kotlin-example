package undecided.reletionship.parsonrole.infra.query.employee;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import undecided.reletionship.parsonrole.business.query.employee.EmployeeDto;
import undecided.reletionship.parsonrole.business.query.employee.EmployeeQuery;
import undecided.reletionship.parsonrole.model.employee.Employee;
import undecided.reletionship.parsonrole.model.employee.EmployeeRepository;
import undecided.reletionship.party.model.person.Person;
import undecided.reletionship.party.model.person.PersonRepository;

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
                    Optional<Person> person = personRepository.findById(employee.getId());
                    return EmployeeDto.reconstruct(employee, person.orElseThrow());
                }).collect(Collectors.toList());

    }

    @Override
    @Observed
    public EmployeeDto findByEmployeeNo(String employeeNo) {
        Employee employee = employeeRepository.findByEmployeeNo(employeeNo);
        Optional<Person> personOptional = personRepository.findById(employee.getId());
        return EmployeeDto.reconstruct(employee, personOptional.orElseThrow());

    }

}
