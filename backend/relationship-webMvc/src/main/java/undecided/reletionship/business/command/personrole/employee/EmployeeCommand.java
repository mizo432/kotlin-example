package undecided.reletionship.business.command.personrole.employee;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import undecided.reletionship.model.parsonrole.employee.Employee;
import undecided.reletionship.model.parsonrole.employee.EmployeeRepository;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;

import java.util.Optional;

@Service
public class EmployeeCommand {
    private final PersonRepository personRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeCommand(PersonRepository personRepository, EmployeeRepository employeeRepository) {
        this.personRepository = personRepository;
        this.employeeRepository = employeeRepository;
    }

    @Observed
    public Employee update(String employeeNo, Employee entity) {
        return null;

    }

    @Observed
    public Employee insert(Employee employee) {
        return null;

    }

    public void deleteByEmployeeNo(String employeeNo) {
        employeeRepository.deleteByEmployeeNo(employeeNo);
    }

    public Employee updateEmployee(String employeeNo, Employee employee) {
        Employee oldEmployee = employeeRepository.findByEmployeeNo(employeeNo);

        return employeeRepository.save(employee);
    }

    public Person updatePerson(Person person) {
        Optional<Person> oldPersonOptional = personRepository.findById(person.getPersonId());
        return personRepository.save(person);

    }

    public Employee insertEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Person insertPerson(Person person) {
        return personRepository.save(person);
    }
}
