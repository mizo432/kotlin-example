package com.undecided.employee.service;

import com.undecided.employee.model.depertment.Department;
import com.undecided.employee.model.employee.Employee;
import com.undecided.employee.model.prefecture.PrefectureDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWithDepartment {
    private Employee employee;
    private Department department;
    private PrefectureDto prefecture;

    public static EmployeeWithDepartment reconstruct(Employee employee, Department department, PrefectureDto prefecture) {
        return new EmployeeWithDepartment(employee, department, prefecture);
    }
}
