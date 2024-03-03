package undecided.reletionship.model.parsonrole.employee;

import org.junit.jupiter.api.Test;
import undecided.reletionship.parsonrole.model.employee.Employee;
import undecided.reletionship.party.model.person.Person;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EmployeeTest {
    /**
     * Method under test: {@link Employee#create(Long, String)}
     */
    @Test
    void create() {
        // Arrange and Act
        Employee actualCreateResult = Employee.create(1L, "Employee No");

        // Assert
        assertEquals("Employee No", actualCreateResult.getEmployeeNo());
        assertEquals(1L, actualCreateResult.getId().longValue());
    }

    /**
     * Method under test: {@link Employee#create(String, String, String, LocalDate)}
     */
    @Test
    void create2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        Employee.create("Employee No", "Jane", "Doe", LocalDate.of(1970, 1, 1));
    }

    /**
     * Method under test: {@link Employee#create(String, Person)}
     */
    @Test
    void create3() {
        // Arrange
        Person person = new Person();
        person.setDateOfBirth(LocalDate.of(1970, 1, 1));
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPersonId(1L);

        // Act
        Employee actualCreateResult = Employee.create("Employee No", person);

        // Assert
        assertEquals("Employee No", actualCreateResult.getEmployeeNo());
        assertEquals(1L, actualCreateResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link Employee#createAtUpdate(Long, String, String, String, LocalDate)}
     */
    @Test
    void createAtUpdate() {
        // Arrange and Act
        Employee actualCreateAtUpdateResult = Employee.createAtUpdate(1L, "Employee No", "Jane", "Doe",
                LocalDate.of(1970, 1, 1));

        // Assert
        assertEquals("Employee No", actualCreateAtUpdateResult.getEmployeeNo());
        assertEquals(1L, actualCreateAtUpdateResult.getId().longValue());
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void equals() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmployeeNo("Employee No");

        // Act and Assert
        assertNotEquals(employee, null);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void equals2() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmployeeNo("Employee No");

        // Act and Assert
        assertNotEquals(employee, "Different type to Employee");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Employee#equals(Object)}
     *   <li>{@link Employee#hashCode()}
     * </ul>
     */
    @Test
    void equals3() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmployeeNo("Employee No");

        // Act and Assert
        assertEquals(employee, employee);
        int expectedHashCodeResult = employee.hashCode();
        assertEquals(expectedHashCodeResult, employee.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Employee#equals(Object)}
     *   <li>{@link Employee#hashCode()}
     * </ul>
     */
    @Test
    void equals4() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmployeeNo("Employee No");

        Employee employee2 = new Employee();
        employee2.setId(1L);
        employee2.setEmployeeNo("Employee No");

        // Act and Assert
        assertEquals(employee, employee2);
        int expectedHashCodeResult = employee.hashCode();
        assertEquals(expectedHashCodeResult, employee2.hashCode());
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void equals5() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setEmployeeNo("Employee No");

        Employee employee2 = new Employee();
        employee2.setId(1L);
        employee2.setEmployeeNo("Employee No");

        // Act and Assert
        assertNotEquals(employee, employee2);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void equals6() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(null);
        employee.setEmployeeNo("Employee No");

        Employee employee2 = new Employee();
        employee2.setId(1L);
        employee2.setEmployeeNo("Employee No");

        // Act and Assert
        assertNotEquals(employee, employee2);
    }
}
