package com.undecided.employee.service;

import com.undecided.employee.model.depertment.Department;
import com.undecided.employee.model.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import reactor.core.publisher.Mono;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAssy {
    private Employee employee;
    private Department department;

    public static Mono<EmployeeAssy> reconstruct(Employee employee, Department department) {
        return Mono.just(new EmployeeAssy(employee, department));
    }
}
