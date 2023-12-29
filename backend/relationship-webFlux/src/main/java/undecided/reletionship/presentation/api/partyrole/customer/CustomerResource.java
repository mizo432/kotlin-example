package undecided.reletionship.presentation.api.partyrole.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import undecided.reletionship.business.command.partyrole.customer.CustomerCommand;
import undecided.reletionship.business.query.partyrole.customer.CustomerQuery;

@RestController
@RequestMapping("/api/v1/party-role//customers")
public class CustomerResource {
    private final CustomerCommand customerCommand;
    private final CustomerQuery customerQuery;

    public CustomerResource(CustomerCommand customerCommand, CustomerQuery customerQuery) {
        this.customerCommand = customerCommand;
        this.customerQuery = customerQuery;
    }
}
