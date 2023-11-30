package com.undecided.employee.presentation.api;

import com.undecided.employee.model.employee.Employee;
import com.undecided.employee.model.employee.EmployeeRepository;
import com.undecided.employee.service.EmployeeAssy;
import com.undecided.employee.service.EmployeeQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeQuery employeeQuery;

    @GetMapping(path = "/api/v1/employees")
    List<Employee> findAll() {
        return employeeRepository.findAll();

    }

    @PostMapping(path = "/api/v1/employees")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    // Single item

    @GetMapping(path = "/api/v1/employees/{id}")
    EmployeeAssy one(@PathVariable Long id) {
        return employeeQuery.findOneBy(id).block();

    }

    @PutMapping(path = "/api/v1/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    Employee replaceEmployee(@RequestBody Employee newDepartment, @PathVariable Long id) {

        return employeeRepository.findById(id)
                .map(employee -> {
                    return employeeRepository.save(newDepartment);
                })
                .orElseGet(() -> {
                    return employeeRepository.save(newDepartment);
                });
    }

    @DeleteMapping(path = "/api/v1/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);

    }
}
