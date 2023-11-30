package com.undecided.employee.model.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    Long EmployeeId;
    String name;
    Long departmentId;

    public static Employee create(long employeeId, String name, long departmentId) {
        return new Employee(employeeId,name,departmentId);
    }
}
