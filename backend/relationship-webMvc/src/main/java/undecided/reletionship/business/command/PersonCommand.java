package undecided.reletionship.business.command;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;

import java.util.Optional;

@Service
public class PersonCommand {

    public static final Logger log = LoggerFactory.getLogger(PersonCommand.class);

    private final PersonRepository personRepository;

    public PersonCommand(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    @Observed
    public Person insert(Person person) {
        log.info("call insert()");
        return personRepository.save(person);
    }


    @Observed
    public Person update(Long id, Person person) {
        log.info("call update()");
        Optional<Person> oldPersonOptional = personRepository.findById(id);
        Person oldPerson = oldPersonOptional.orElseThrow();
        return personRepository.save(oldPerson.update(person));
    }

    @Observed
    public void delete(Long id) {
        log.info("call delete()");
        Optional<Person> oldPersonOptional = personRepository.findById(id);
        Person oldPerson = oldPersonOptional.orElseThrow();
        personRepository.deleteById(oldPerson.getPersonId());
    }
}

