package undecided.adjustment.account.presentation.api.taxrate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import undecided.adjustment.account.business.query.TaxRateQuery;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account/tax-rates")
public class TaxRateResource {
    private final TaxRateQuery taxRateQuery;

    public TaxRateResource(TaxRateQuery taxRateQuery) {
        this.taxRateQuery = taxRateQuery;
    }

    @GetMapping
    public List<TaxRateDto> getAll() {
        return TaxRateDto.reconstruct(taxRateQuery.findAll());

    }

    @GetMapping("/{id}")
    public TaxRateDto getById(@PathVariable("id") Long taxRateId) {
        return TaxRateDto.reconstruct(taxRateQuery.findById(taxRateId));

    }

}
