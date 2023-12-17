package undicided.reletionship.infra.query.partyrole.customer;

import org.springframework.stereotype.Service;
import undicided.reletionship.business.query.partyrole.customer.CustomerQuery;
import undicided.reletionship.model.partyrole.customer.CustomerRepository;

@Service
public class CustomerQueryImpl implements CustomerQuery {
    private final CustomerRepository customerRepository;

    public CustomerQueryImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        
    }
}
