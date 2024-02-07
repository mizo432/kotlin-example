package undecided.reletionship.party.infra.query.person;

import org.springframework.stereotype.Service;
import undecided.reletionship.party.business.query.person.PersonQuery;
import undecided.reletionship.party.model.person.Person;
import undecided.reletionship.party.model.person.PersonRepository;

import java.util.List;

@Service
public class PersonQueryImpl implements PersonQuery {
    private final PersonRepository personRepository;

    public PersonQueryImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow();
        
    }
}
