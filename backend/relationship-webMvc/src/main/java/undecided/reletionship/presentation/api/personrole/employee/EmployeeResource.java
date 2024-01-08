package undecided.reletionship.presentation.api.personrole.employee;

import io.micrometer.observation.annotation.Observed;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import undecided.reletionship.business.command.personrole.employee.EmployeeCommand;
import undecided.reletionship.business.query.personrole.employee.EmployeeDto;
import undecided.reletionship.business.query.personrole.employee.EmployeeQuery;
import undecided.reletionship.model.parsonrole.employee.Employee;
import undecided.reletionship.model.party.person.Person;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person-role/employees")
public class EmployeeResource {
    private final EmployeeQuery employeeQuery;
    private final EmployeeCommand employeeCommand;

    public EmployeeResource(EmployeeQuery employeeQuery, EmployeeCommand employeeCommand) {
        this.employeeQuery = employeeQuery;
        this.employeeCommand = employeeCommand;
    }

    @GetMapping
    @Observed
    public List<EmployeeDto> get() {
        return employeeQuery.findAll();

    }

    @GetMapping("/{employeeNo}")
    @Observed
    public EmployeeDto get(@PathVariable("employeeNo") String employeeNo) {
        return employeeQuery.findByEmployeeNo(employeeNo);

    }

    @PostMapping
    @Observed
    public EmployeeDto post(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeCommand
                .insertEmployee(employeeDto.toEmployeeAtInsert());
        Person person = employeeCommand
                .insertPerson(employeeDto.toPersonAtInsert(employee.getEmployeeId()));
        return EmployeeDto.reconstruct(employee, person);

    }

    @PutMapping("/{employeeNo}")
    @Observed
    public EmployeeDto put(@PathVariable("employeeNo") String employeeNo, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeCommand
                .updateEmployee(employeeNo, employeeDto.toEmployeeAtUpdate());
        Person person = employeeCommand
                .updatePerson(employeeDto.toPersonAtUpdate());
        return EmployeeDto.reconstruct(employee, person);

    }

    @DeleteMapping("/{employeeNo}")
    @Observed
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("employeeNo") String employeeNo) {
        employeeCommand.deleteByEmployeeNo(employeeNo);

    }

}
