package undecided.adjustment.account.business.query;

import undecided.adjustment.account.model.taxrate.TaxRate;

import java.util.List;

public interface TaxRateQuery {
    TaxRate findById(Long taxRateId);

    List<TaxRate> findAll();
}
