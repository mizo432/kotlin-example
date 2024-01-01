package undecided.adjustment.account.infra.query.taxrate;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import undecided.adjustment.account.business.query.TaxRateQuery;
import undecided.adjustment.account.model.taxrate.TaxRate;
import undecided.adjustment.account.model.taxrate.TaxRateRepository;

import java.util.List;

@Service
@Observed(name = "taxRateQuery", contextualName = "taxRateQuery")
public class TaxRateQueryImpl implements TaxRateQuery {
    private final TaxRateRepository taxRateRepository;

    public TaxRateQueryImpl(TaxRateRepository taxRateRepository) {
        this.taxRateRepository = taxRateRepository;

    }

    @Override
    public TaxRate findById(Long taxRateId) {
        return taxRateRepository.findById(taxRateId).orElseThrow();
    }

    @Override
    public List<TaxRate> findAll() {
        return taxRateRepository.findAll();
    }
}
