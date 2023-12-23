package undicided.reletionship.model.parsonrole.employee;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import undicided.reletionship.model.parsonrole.PersonRole;
import undicided.reletionship.model.party.person.Person;

import java.time.LocalDate;

@Table(schema = "relationship", name = "employees")
public class Employee implements PersonRole<Employee> {
    @Id
    private Long employeeId;

    @Column("employee_no")
    private String employeeNo;
    @Transient
    private Person person;

    public static Employee create(String employeeNo, String firstName, String lastName, LocalDate dateOfBirth) {
        Person person = Person.createAtInsert(firstName, lastName, dateOfBirth);
        return Employee.create(employeeNo, person);
    }

    public static Employee create(String employeeNo, Person person) {
        Employee employee = new Employee();
        employee.setEmployeeId(person.personId());
        employee.setEmployeeNo(employeeNo);
        employee.setPerson(person);
        return employee;
    }

    public static Employee createAtUpdate(Long employeeId, String employeeNo, String firstName, String lastName, LocalDate dateOfBirth) {
        Person person = Person.createAtUpdate(employeeId, firstName, lastName, dateOfBirth);
        return Employee.create(employeeNo, person);

    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
