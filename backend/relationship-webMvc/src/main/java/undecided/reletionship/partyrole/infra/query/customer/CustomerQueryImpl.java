package undecided.reletionship.partyrole.infra.query.customer;

import org.springframework.stereotype.Service;
import undecided.reletionship.partyrole.business.query.customer.CustomerQuery;
import undecided.reletionship.partyrole.model.customer.CustomerRepository;

@Service
public class CustomerQueryImpl implements CustomerQuery {
    private final CustomerRepository customerRepository;

    public CustomerQueryImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }
}
