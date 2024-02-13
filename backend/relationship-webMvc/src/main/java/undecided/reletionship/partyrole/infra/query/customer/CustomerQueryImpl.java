package undecided.reletionship.partyrole.infra.query.customer;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import undecided.reletionship.party.model.Party.Parties;
import undecided.reletionship.party.model.PartyRepository;
import undecided.reletionship.party.model.person.Person;
import undecided.reletionship.party.model.person.PersonRepository;
import undecided.reletionship.partyrole.business.query.customer.CustomerQuery;
import undecided.reletionship.partyrole.business.query.customer.SimpleCustomer;
import undecided.reletionship.partyrole.model.customer.Customer.Customers;
import undecided.reletionship.partyrole.model.customer.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerQueryImpl implements CustomerQuery {
    private final CustomerRepository customerRepository;

    private final PartyRepository partyRepository;
    private final PersonRepository personRepository;

    public CustomerQueryImpl(CustomerRepository customerRepository, PartyRepository partyRepository, PersonRepository personRepository) {
        this.customerRepository = customerRepository;
        this.partyRepository = partyRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<SimpleCustomer> findAll() {
        List<SimpleCustomer> result = new ArrayList<>();
        Customers customers = Customers.of(customerRepository.findAll(Sort.by("customer_code")));
        Parties parties = Parties.of(partyRepository.findAllById(customers.ids()));
        Parties customerPeople = parties.customerPeople();
        List<Person> personList = personRepository.findAllById(parties.ids());

        Parties customerOrganizationUnits = parties.customerOrganizationUnits();


        return result;

    }
}
