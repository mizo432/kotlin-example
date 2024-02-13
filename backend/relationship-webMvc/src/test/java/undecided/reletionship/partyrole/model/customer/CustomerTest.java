package undecided.reletionship.partyrole.model.customer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer.Customers customers;

    /**
     * Method under test: {@link Customer.Customers#Customers(Collection)}
     */
    @Test
    void CustomersConstructor() {
        Customer.Customers target = new Customer.Customers(new ArrayList<>());
        assertTrue(target.value.isEmpty());
    }

    /**
     * Method under test: {@link Customer.Customers#ids()}
     */
    @Test
    void CustomersIds() {
        Customer.Customers target = new Customer.Customers(new ArrayList<>());
        // Arrange and Act
        Set<Long> actual = target.ids();
        assertTrue(actual.isEmpty());

    }

    /**
     * Method under test: {@link Customer.Customers#of(List)}
     */
    @Test
    void CustomersOf() {
        // Arrange
        ArrayList<Customer> value = new ArrayList<>();

        // Act and Assert
        List<Customer> customerList = Customer.Customers.of(value).value;
        assertTrue(customerList.isEmpty());
        assertEquals(value, customerList);
    }

    /**
     * Method under test: {@link Customer.Customers#of(List)}
     */
    @Test
    void CustomersOf2() {
        // Arrange
        Customer customer = new Customer();
        customer.setCode("Code");
        customer.setId(1L);

        ArrayList<Customer> value = new ArrayList<>();
        value.add(customer);

        // Act and Assert
        List<Customer> customerList = Customer.Customers.of(value).value;
        assertEquals(1, customerList.size());
        assertEquals(value, customerList);
    }

    /**
     * Method under test: {@link Customer.Customers#of(List)}
     */
    @Test
    void CustomersOf3() {
        // Arrange
        Customer customer = new Customer();
        customer.setCode("Code");
        customer.setId(1L);

        Customer customer2 = new Customer();
        customer2.setCode("42");
        customer2.setId(2L);

        ArrayList<Customer> value = new ArrayList<>();
        value.add(customer2);
        value.add(customer);

        // Act and Assert
        List<Customer> customerList = Customer.Customers.of(value).value;
        assertEquals(2, customerList.size());
        assertEquals(value, customerList);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void equals() {
        // Arrange
        Customer customer = new Customer();
        customer.setCode("Code");
        customer.setId(1L);

        // Act and Assert
        assertNotEquals(customer, null);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void equals2() {
        // Arrange
        Customer customer = new Customer();
        customer.setCode("Code");
        customer.setId(1L);

        // Act and Assert
        assertNotEquals(customer, "Different type to Customer");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Customer#equals(Object)}
     *   <li>{@link Customer#hashCode()}
     * </ul>
     */
    @Test
    void equals3() {
        // Arrange
        Customer customer = new Customer();
        customer.setCode("Code");
        customer.setId(1L);

        // Act and Assert
        assertEquals(customer, customer);
        int expectedHashCodeResult = customer.hashCode();
        assertEquals(expectedHashCodeResult, customer.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Customer#equals(Object)}
     *   <li>{@link Customer#hashCode()}
     * </ul>
     */
    @Test
    void equals4() {
        // Arrange
        Customer customer = new Customer();
        customer.setCode("Code");
        customer.setId(1L);

        Customer customer2 = new Customer();
        customer2.setCode("Code");
        customer2.setId(1L);

        // Act and Assert
        assertEquals(customer, customer2);
        int expectedHashCodeResult = customer.hashCode();
        assertEquals(expectedHashCodeResult, customer2.hashCode());
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void equals5() {
        // Arrange
        Customer customer = new Customer();
        customer.setCode("Code");
        customer.setId(2L);

        Customer customer2 = new Customer();
        customer2.setCode("Code");
        customer2.setId(1L);

        // Act and Assert
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void equals6() {
        // Arrange
        Customer customer = new Customer();
        customer.setCode("Code");
        customer.setId(null);

        Customer customer2 = new Customer();
        customer2.setCode("Code");
        customer2.setId(1L);

        // Act and Assert
        assertNotEquals(customer, customer2);
    }
}
