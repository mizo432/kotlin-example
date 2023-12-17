package undicided.reletionship.model.partyrole.customer;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "CUSTOMERS", schema = "relationship")
public record Customer(
        @Id
        Long customerId,
        @Column("CUSTOMER_CODE")
        String code) {

}
