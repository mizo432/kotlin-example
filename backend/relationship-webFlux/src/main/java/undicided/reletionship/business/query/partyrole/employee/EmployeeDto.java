package undicided.reletionship.business.query.partyrole.employee;

import undicided.reletionship.model.parsonrole.employee.Employee;

public record EmployeeDto(String employeeCode, String firstName, String lastName) {

    public Employee toEntity() {
        return null;
    }
}
