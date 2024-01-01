package undecided.adjustment.account.model.taxrate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(schema = "adjustment", name = "TAX_RATE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxRate {
    @Id
    private Long taxRateId;
    private Short percentage;
    private String taxKind;
    private String displayName;
    private LocalDate startDate;
    private LocalDate endDate;

}
