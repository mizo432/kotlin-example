package undecided.accountmgmt.account.presentation.api.taxrate;

import undecided.accountmgmt.account.model.taxrate.TaxRate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record TaxRateDto(Long taxRateId, Short percentage, String taxKind, String displayName, LocalDate startDate,
                         LocalDate endDate) {

    public static TaxRateDto reconstruct(TaxRate taxRate) {
        return new TaxRateDto(taxRate.getTaxRateId(), taxRate.getPercentage(), taxRate.getTaxKind(), taxRate.getDisplayName(), taxRate.getStartDate(), taxRate.getEndDate());

    }

    public static List<TaxRateDto> reconstruct(List<TaxRate> taxRates) {
        List<TaxRateDto> result = new ArrayList<>();
        for (TaxRate taxRate : taxRates) {
            result.add(TaxRateDto.reconstruct(taxRate));
        }
        return result;
    }
}
