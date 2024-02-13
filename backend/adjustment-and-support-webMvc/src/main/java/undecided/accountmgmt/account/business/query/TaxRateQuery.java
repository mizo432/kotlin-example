package undecided.accountmgmt.account.business.query;

import undecided.accountmgmt.account.model.taxrate.TaxRate;

import java.util.List;

public interface TaxRateQuery {
    TaxRate findById(Long taxRateId);

    List<TaxRate> findAll();
}
