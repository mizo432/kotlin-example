package undecided.reletionship.partyrole.presentation.web.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import undecided.reletionship.partyrole.business.query.customer.CustomerQuery;
import undecided.reletionship.partyrole.business.query.customer.SimpleCustomer;

import java.util.List;

@Controller
@RequestMapping("/relationship/customers")
public class CustomerController {
    private final CustomerQuery customerQuery;

    public CustomerController(CustomerQuery customerQuery) {
        this.customerQuery = customerQuery;
    }

    @RequestMapping(method = RequestMethod.GET)
    String list(@ModelAttribute Model model) {
        List<SimpleCustomer> customerList = customerQuery.findAll();
        model.addAttribute("customers", customerList);
        return "list";
    }

}
