package undecided.reletionship.infra.query.person;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import undecided.reletionship.business.query.person.PersonQuery;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;
import undecided.shared.entity.id.SnowflakeId;

import java.util.List;

@Service
public class PersonQueryImpl implements PersonQuery {

    private final PersonRepository personRepository;

    public PersonQueryImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Observed
    public List<Person> findAll() {
        return personRepository.findAll();

    }

    @Override
    @Observed
    public Person findById(SnowflakeId<Person> id) {
        return personRepository.findById(id)
                .orElseThrow();

    }

}
