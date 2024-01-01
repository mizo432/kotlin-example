package undecided.adjustment.account.presentation.api.taxrate;

import undecided.adjustment.account.model.taxrate.TaxRate;

import java.time.LocalDate;

public record TaxRateDto(Long taxRateId, Short percentage, String taxKind, String displayName, LocalDate startDate,
                         LocalDate endDate) {

    public static TaxRateDto reconstruct(TaxRate taxRate) {
        return new TaxRateDto(taxRate.taxRateId(), taxRate.percentage(), taxRate.taxKind(), taxRate.displayName(), taxRate.startDate(), taxRate.endDate());

    }
}
