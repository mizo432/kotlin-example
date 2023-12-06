package com.undecided.employee.model.employee;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "create")
@NoArgsConstructor
@ToString
public class Employee {
    private Long EmployeeId;
    private String name;
    private Long departmentId;

    public static Employee create(long employeeId, String name, long departmentId) {
        return new Employee(employeeId, name, departmentId);
    }
}
