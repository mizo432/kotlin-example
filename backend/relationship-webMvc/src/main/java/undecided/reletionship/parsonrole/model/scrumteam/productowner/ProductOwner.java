package undecided.reletionship.parsonrole.model.scrumteam.productowner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import undecided.reletionship.parsonrole.model.PersonRole;

@Entity
@Table(schema = "relationship", name = "product_owners")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductOwner implements PersonRole<ProductOwner> {
    @Id
    @Column(name = "product_owner_id", nullable = false)
    private Long id;

    @Column(name = "employee_no")
    private String employeeNo;

}
