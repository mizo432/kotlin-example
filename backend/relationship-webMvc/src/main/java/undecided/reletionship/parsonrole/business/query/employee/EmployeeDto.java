package undecided.reletionship.parsonrole.business.query.employee;

import undecided.reletionship.parsonrole.model.employee.Employee;
import undecided.reletionship.party.model.person.Person;

import java.time.LocalDate;

public record EmployeeDto(Long employeeId, String employeeNo, String firstName, String lastName,
                          LocalDate dateOfBirth) {

    public static EmployeeDto reconstruct(Employee employee, Person person) {
        return new EmployeeDto(employee.getEmployeeId(), employee.getEmployeeNo(), person.getFirstName(), person.getLastName(), person.getDateOfBirth());

    }

    public Employee toEntityAtInsert() {
        return Employee.create(employeeNo, firstName, lastName, dateOfBirth);
    }

    public Employee toEntityAtUpdate() {
        return Employee.createAtUpdate(employeeId, employeeNo, firstName, lastName, dateOfBirth);

    }

    public Employee toEmployeeAtUpdate() {
        return Employee.create(employeeId, employeeNo);

    }

    public Person toPersonAtUpdate() {
        return Person.createAtUpdate(employeeId, firstName, lastName, dateOfBirth);

    }

    public Employee toEmployeeAtInsert() {
        return Employee.createAtInsert(employeeNo);

    }

    public Person toPersonAtInsert(Long employeeId) {
        return Person.createAtUpdate(employeeId, firstName, lastName, dateOfBirth);
    }
}
