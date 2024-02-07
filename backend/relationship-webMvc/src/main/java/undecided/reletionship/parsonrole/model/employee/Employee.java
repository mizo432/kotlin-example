package undecided.reletionship.parsonrole.model.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import undecided.reletionship.parsonrole.model.PersonRole;
import undecided.reletionship.party.model.person.Person;
import undecided.shared.entity.id.SnowflakeId;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(schema = "relationship", name = "employees")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Employee employee = (Employee) o;
        return getEmployeeId() != null && Objects.equals(getEmployeeId(), employee.getEmployeeId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
