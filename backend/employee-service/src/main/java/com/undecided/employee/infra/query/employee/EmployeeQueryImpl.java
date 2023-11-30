package com.undecided.employee.infra.query.employee;

import com.undecided.employee.model.depertment.Department;
import com.undecided.employee.model.depertment.DepartmentClient;
import com.undecided.employee.model.employee.Employee;
import com.undecided.employee.model.employee.EmployeeRepository;
import com.undecided.employee.service.EmployeeAssy;
import com.undecided.employee.service.EmployeeQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@AllArgsConstructor
@Service
public class EmployeeQueryImpl implements EmployeeQuery {
    private final EmployeeRepository employeeRepository;
    private final DepartmentClient departmentClient;

    @Override
    public Mono<EmployeeAssy> findOneBy(Long id) {
        return Mono.just(employeeRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("employees:" + id)))
                .flatMap((Function<Employee, Mono<EmployeeAssy>>) employee -> {
                    Long departmentId = employee.getDepartmentId();
                    Mono<Department> departmentMono = departmentClient.findOneById(departmentId);
                    return EmployeeAssy.reconstruct(employee, departmentMono);
                });
    }
}
