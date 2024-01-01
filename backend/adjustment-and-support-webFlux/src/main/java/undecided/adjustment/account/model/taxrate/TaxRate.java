package undecided.adjustment.account.model.taxrate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(schema = "adjustment", name = "TAX_RATE")
public record TaxRate(@Id Long taxRateId, Short percentage, String taxKind, String displayName, LocalDate startDate,
                      LocalDate endDate) {

}
