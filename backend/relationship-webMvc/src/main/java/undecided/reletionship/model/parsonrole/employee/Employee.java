package undecided.reletionship.model.parsonrole.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import undecided.reletionship.model.parsonrole.PersonRole;
import undecided.reletionship.model.party.person.Person;
import undecided.shared.entity.id.SnowflakeId;

import java.time.LocalDate;

@Entity
@Table(schema = "relationship", name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements PersonRole<Employee> {
    @Id
    private Long employeeId;

    @Column(name = "employee_no")
    private String employeeNo;

    public static Employee create(String employeeNo, String firstName, String lastName, LocalDate dateOfBirth) {
        Person person = Person.createAtInsert(firstName, lastName, dateOfBirth);
        return Employee.create(employeeNo, person);
    }

    public static Employee create(String employeeNo, Person person) {
        Employee employee = new Employee();
        employee.setEmployeeId(person.getPersonId());
        employee.setEmployeeNo(employeeNo);
        return employee;
    }

    public static Employee create(Long personId, String employeeNo) {
        return new Employee(personId, employeeNo);
    }

    public static Employee createAtUpdate(Long employeeId, String employeeNo, String firstName, String lastName, LocalDate dateOfBirth) {
        Person person = Person.createAtUpdate(employeeId, firstName, lastName, dateOfBirth);
        return Employee.create(employeeNo, person);

    }


    public static Employee createAtInsert(String employeeNo) {
        return new Employee(SnowflakeId.newInstance().getValue(), employeeNo);
    }
}
