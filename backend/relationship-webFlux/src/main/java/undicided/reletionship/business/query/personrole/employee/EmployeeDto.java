package undicided.reletionship.business.query.personrole.employee;

import undicided.reletionship.model.parsonrole.employee.Employee;
import undicided.reletionship.model.party.person.Person;

import java.time.LocalDate;

public record EmployeeDto(Long employeeId, String employeeNo, String firstName, String lastName,
                          LocalDate dateOfBirth) {

    public static EmployeeDto reconstruct(Employee employee, Person person) {
        return new EmployeeDto(employee.getEmployeeId(), employee.getEmployeeNo(), person.firstName(), person.lastName(), person.dateOfBirth());

    }

    public static EmployeeDto reconstruct(Employee employee) {
        return reconstruct(employee, employee.getPerson());
    }

    public Employee toEntityAtInsert() {
        return Employee.create(employeeNo, firstName, lastName, dateOfBirth);
    }

    public Employee toEntityAtUpdate() {
        return Employee.createAtUpdate(employeeId, employeeNo, firstName, lastName, dateOfBirth);
        
    }
}
