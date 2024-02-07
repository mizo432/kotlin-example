package undecided.reletionship.party.model.organization;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(schema = "relationship", name = "organization")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Organization {

    @Id
    @Column(name = "organization_id")
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isTaxExempt;


}
