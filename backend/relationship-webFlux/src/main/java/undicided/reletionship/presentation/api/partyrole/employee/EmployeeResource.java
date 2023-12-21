package undicided.reletionship.presentation.api.partyrole.employee;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undicided.reletionship.business.command.partyrole.employee.EmployeeCommand;
import undicided.reletionship.business.query.partyrole.employee.EmployeeDto;
import undicided.reletionship.business.query.partyrole.employee.EmployeeQuery;

@RestController
@RequestMapping("/employees")
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

    @GetMapping("/{employeeCode}")
    public Mono<EmployeeDto> get(@PathVariable("employeeCode") String employeeCode) {
        return employeeQuery.findOneBy(employeeCode);
    }

    @PostMapping
    public Mono<EmployeeDto> post(@RequestBody EmployeeDto employeeDto) {
        return employeeCommand.post(employeeDto);
    }

    @PutMapping("/{employeeCode}")
    public Mono<EmployeeDto> put(@PathVariable("employeeCode") String employeeCode, @RequestBody EmployeeDto employeeDto) {
        return employeeCommand.save(employeeCode, employeeDto.toEntity());


    }

    @DeleteMapping("/{employeeCode}")
    public Mono<Void> delete(@PathVariable("employeeCode") String employeeCode) {
        return employeeCommand.delete(employeeCode);

    }

}
