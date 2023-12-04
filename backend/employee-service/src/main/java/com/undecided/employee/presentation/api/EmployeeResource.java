package com.undecided.employee.presentation.api;

import com.undecided.employee.model.employee.Employee;
import com.undecided.employee.model.employee.EmployeeRepository;
import com.undecided.employee.service.EmployeeQuery;
import com.undecided.employee.service.EmployeeWithDepartment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeResource {

    private final EmployeeRepository employeeRepository;
    private final EmployeeQuery employeeQuery;

    @GetMapping
    Flux<Employee> findAll() {
        return employeeRepository.findAll();

    }

    @GetMapping(path = "/with-department")
    Flux<EmployeeWithDepartment> findAllWithDepartment() {
        return employeeQuery.getEmployeesWithDepartment();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    // Single item

    @GetMapping(path = "/{id}")
    Mono<EmployeeWithDepartment> one(@PathVariable Long id) {
        return employeeQuery.findOneBy(id);

    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    Employee replaceEmployee(@RequestBody Employee newDepartment, @PathVariable Long id) {
        return employeeRepository.save(newDepartment);

    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);

    }
}
