package undecided.adjustment.account.infra.query.taxrate;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adjustment.account.business.query.TaxRateQuery;
import undecided.adjustment.account.model.taxrate.TaxRate;
import undecided.adjustment.account.model.taxrate.TaxRateRepository;

@Service
@Observed(name = "taxRateQuery", contextualName = "taxRateQuery")
public class TaxRateQueryImpl implements TaxRateQuery {
    private final TaxRateRepository taxRateRepository;

    public TaxRateQueryImpl(TaxRateRepository taxRateRepository) {
        this.taxRateRepository = taxRateRepository;

    }

    @Override
    public Mono<TaxRate> findById(Long taxRateId) {
        return taxRateRepository.findById(taxRateId);
    }

    @Override
    public Flux<TaxRate> findAll() {
        return taxRateRepository.findAll();
    }
}
