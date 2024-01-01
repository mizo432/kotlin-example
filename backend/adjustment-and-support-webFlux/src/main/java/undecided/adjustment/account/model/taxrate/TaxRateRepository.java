package undecided.adjustment.account.model.taxrate;


import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TaxRateRepository extends R2dbcRepository<TaxRate, Long> {
}
