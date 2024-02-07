package undecided.reletionship.partyrole.model.customer;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "CUSTOMERS", schema = "relationship")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    Long customerId;
    @Column(name = "CUSTOMER_CODE")
    String code;

}
