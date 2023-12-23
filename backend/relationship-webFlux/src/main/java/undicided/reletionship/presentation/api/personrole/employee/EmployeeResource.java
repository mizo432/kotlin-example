package undicided.reletionship.presentation.api.personrole.employee;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undicided.reletionship.business.command.personrole.employee.EmployeeCommand;
import undicided.reletionship.business.query.personrole.employee.EmployeeDto;
import undicided.reletionship.business.query.personrole.employee.EmployeeQuery;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeResource {
    private final EmployeeQuery employeeQuery;
    private final EmployeeCommand employeeCommand;

    public EmployeeResource(EmployeeQuery employeeQuery, EmployeeCommand employeeCommand) {
        this.employeeQuery = employeeQuery;
        this.employeeCommand = employeeCommand;
    }

    @GetMapping
    public Flux<EmployeeDto> get() {
        return employeeQuery.findAll();

    }

    @GetMapping("/{employeeNo}")
    public Mono<EmployeeDto> get(@PathVariable("employeeNo") String employeeNo) {
        return employeeQuery.findOneByEmployeeNo(employeeNo);

    }

    @PostMapping
    public Mono<EmployeeDto> post(@RequestBody EmployeeDto employeeDto) {
        return employeeCommand
                .insert(employeeDto.toEntityAtInsert())
                .map(EmployeeDto::reconstruct);

    }

    @PutMapping("/{employeeNo}")
    public Mono<EmployeeDto> put(@PathVariable("employeeNo") String employeeNo, @RequestBody EmployeeDto employeeDto) {
        return employeeCommand
                .update(employeeNo, employeeDto.toEntityAtUpdate())
                .map(EmployeeDto::reconstruct);

    }

    @DeleteMapping("/{employeeNo}")
    public Mono<Void> delete(@PathVariable("employeeNo") String employeeNo) {
        return employeeCommand.delete(employeeNo);

    }

}
