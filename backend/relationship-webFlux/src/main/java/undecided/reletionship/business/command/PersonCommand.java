package undecided.reletionship.business.command;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;

@Service
public class PersonCommand {

    public static final Logger log = LoggerFactory.getLogger(PersonCommand.class);

    private final PersonRepository personRepository;

    public PersonCommand(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    @Observed
    public Mono<Person> insert(Person person) {
        log.info("call insert()");
        return personRepository.save(person);
    }


    @Observed
    public Mono<Person> update(Long id, Person person) {
        log.info("call update()");
        return personRepository.findById(id).flatMap(oldPerson ->
                personRepository.save(oldPerson.update(person)));
    }

    @Observed
    public Mono<Void> delete(Long id) {
        log.info("call delete()");
        return personRepository.findById(id).flatMap(person -> {
            return personRepository.deleteById(person.personId());

        });
    }
}

