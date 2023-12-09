package com.undecided.employee.presentation.api;

import com.undecided.employee.model.employee.Employee;
import com.undecided.employee.model.employee.EmployeeRepository;
import com.undecided.employee.service.EmployeeQuery;
import com.undecided.employee.service.EmployeeWithDepartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeResource {

    private static final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeQuery employeeQuery;

    public EmployeeResource(EmployeeRepository employeeRepository, EmployeeQuery employeeQuery) {
        this.employeeRepository = employeeRepository;
        this.employeeQuery = employeeQuery;
    }

    @GetMapping
    Flux<Employee> getAll() {
        log.info("get all employees.");
        return employeeRepository.findAll();

    }

    @GetMapping(path = "/with-department")
    Flux<EmployeeWithDepartment> gatAllWithDepartment() {
        log.info("get all employees with department.");
        return employeeQuery.findEmployeesWithDepartment();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    Employee postEmployee(@RequestBody Employee newEmployee) {
        log.info("post employee.");
        return employeeRepository.save(newEmployee);

    }

    // Single item

    @GetMapping(path = "/{id}")
    Mono<EmployeeWithDepartment> getOneById(@PathVariable Long id) {
        log.info("get a employee by Id.");

        return employeeQuery.findOneBy(id);

    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    Employee putEmployee(@RequestBody Employee newDepartment, @PathVariable Long id) {
        log.info("put a employee");
        return employeeRepository.save(newDepartment);

    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmployee(@PathVariable Long id) {
        log.info("delete a employee by Id.");
        employeeRepository.deleteById(id);

    }
}
