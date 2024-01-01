package undecided.reletionship.infra.query.partyrole.customer;

import org.springframework.stereotype.Service;
import undecided.reletionship.business.query.partyrole.customer.CustomerQuery;
import undecided.reletionship.model.partyrole.customer.CustomerRepository;

@Service
public class CustomerQueryImpl implements CustomerQuery {
    private final CustomerRepository customerRepository;

    public CustomerQueryImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }
}
