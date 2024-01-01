package undecided.adjustment.account.business.query;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import undecided.adjustment.account.model.taxrate.TaxRate;

public interface TaxRateQuery {
    Mono<TaxRate> findById(Long taxRateId);

    Flux<TaxRate> findAll();
}
