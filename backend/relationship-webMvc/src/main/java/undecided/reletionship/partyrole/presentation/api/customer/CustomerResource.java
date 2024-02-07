package undecided.reletionship.partyrole.presentation.api.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import undecided.reletionship.partyrole.business.command.customer.CustomerCommand;
import undecided.reletionship.partyrole.business.query.customer.CustomerQuery;

@RestController
@RequestMapping("/party-role/api/v1/customers")
public class CustomerResource {
    private final CustomerCommand customerCommand;
    private final CustomerQuery customerQuery;

    public CustomerResource(CustomerCommand customerCommand, CustomerQuery customerQuery) {
        this.customerCommand = customerCommand;
        this.customerQuery = customerQuery;
    }
}
