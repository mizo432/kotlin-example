package undecided.reletionship.business.command;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;
import undecided.shared.entity.id.SnowflakeId;

@Service
public class PersonCommand {
    private final PersonRepository personRepository;

    public PersonCommand(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Observed
    public Person insert(Person person) {
        return personRepository.save(person);
    }

    @Observed
    public Person update(SnowflakeId<Person> id, Person person) {
        Person oldPerson = personRepository.findById(id).orElseThrow();
        Person newPerson = oldPerson.update(person);
        return personRepository.save(newPerson);
    }

    @Observed
    public void delete(SnowflakeId<Person> id) {
        personRepository.findById(id).orElseThrow();
        personRepository.deleteById(id);
    }
}

