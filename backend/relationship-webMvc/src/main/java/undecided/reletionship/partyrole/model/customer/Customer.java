package undecided.reletionship.partyrole.model.customer;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;


@Entity
@Table(name = "CUSTOMERS", schema = "relationship")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    Long id;
    @Column(name = "CUSTOMER_CODE")
    String code;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Customer customer = (Customer) o;
        return getId() != null && Objects.equals(getId(), customer.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public static class Customers {
        List<Customer> value = new ArrayList<>();

        Customers(Collection<Customer> value) {
            this.value.addAll(value);
        }

        public static Customers of(List<Customer> value) {
            return new Customers(value);
        }


        public Set<Long> ids() {
            Set<Long> result = new HashSet<>();
            for (Customer customer : value) {
                result.add(customer.getId());
            }

            return result;

        }
    }

}
