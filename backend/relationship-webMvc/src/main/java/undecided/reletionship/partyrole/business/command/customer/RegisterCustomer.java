package undecided.reletionship.partyrole.business.command.customer;

import org.springframework.stereotype.Service;
import undecided.reletionship.partyrole.model.customer.Customer;

@Service
public class RegisterCustomer {
    Customer execute(Customer customer) {
        return customer;
        
    }
}
