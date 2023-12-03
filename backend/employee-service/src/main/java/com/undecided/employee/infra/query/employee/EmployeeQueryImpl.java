package com.undecided.employee.infra.query.employee;

import com.undecided.employee.model.depertment.DepartmentClient;
import com.undecided.employee.model.employee.EmployeeRepository;
import com.undecided.employee.service.EmployeeQuery;
import com.undecided.employee.service.EmployeeWithDepartment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class EmployeeQueryImpl implements EmployeeQuery {
    private final EmployeeRepository employeeRepository;
    private final DepartmentClient departmentClient;

    @Override
    public Mono<EmployeeWithDepartment> findOneBy(Long id) {
        return employeeRepository.findById(id)
                .flatMap(employee -> {
                    Long departmentId = employee.getDepartmentId();
                    return departmentClient.findOneById(departmentId)
                            .map(department -> EmployeeWithDepartment.reconstruct(employee, department));
                });
    }

    @Override
    public Flux<EmployeeWithDepartment> getEmployeesWithDepartment() {
        return employeeRepository.findAll().flatMap(employee -> {
            Long departmentId = employee.getDepartmentId();
            return departmentClient.findOneById(departmentId)
                    .map(department -> EmployeeWithDepartment.reconstruct(employee, department));
        });
    }
}
