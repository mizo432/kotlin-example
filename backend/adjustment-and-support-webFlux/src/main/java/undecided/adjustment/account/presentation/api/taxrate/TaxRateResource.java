package undecided.adjustment.account.presentation.api.taxrate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adjustment.account.business.query.TaxRateQuery;

@RestController
@RequestMapping("/api/v1/account/tax-rates")
public class TaxRateResource {
    private final TaxRateQuery taxRateQuery;

    public TaxRateResource(TaxRateQuery taxRateQuery) {
        this.taxRateQuery = taxRateQuery;
    }

    @GetMapping
    public Flux<TaxRateDto> getAll() {
        return taxRateQuery.findAll().map(TaxRateDto::reconstruct);

    }

    @GetMapping("/{id}")
    public Mono<TaxRateDto> getById(@PathVariable("id") Long taxRateId) {
        return taxRateQuery.findById(taxRateId).map(TaxRateDto::reconstruct);

    }

}
