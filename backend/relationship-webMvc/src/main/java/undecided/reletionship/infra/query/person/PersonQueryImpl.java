package undecided.reletionship.infra.query.person;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import undecided.reletionship.business.query.person.PersonQuery;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;

import java.util.List;

@Service
public class PersonQueryImpl implements PersonQuery {

    public static final Logger log = LoggerFactory.getLogger(PersonQueryImpl.class);


    private final PersonRepository personRepository;

    public PersonQueryImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    @Override
    @Observed
    public List<Person> findAll() {
        log.info("called findAll()");
        return personRepository.findAll();

    }

    @Override
    @Observed
    public Person findById(Long id) {
        log.info("called findById()");
        return personRepository.findById(id).orElseThrow();

    }

}
