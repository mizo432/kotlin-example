package undecided.reletionship.infra.datasource.person;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Component;
import undecided.reletionship.infra.dao.person.PersonDao;
import undecided.reletionship.infra.dao.person.PersonTable;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;
import undecided.shared.entity.id.SnowflakeId;

import java.util.List;
import java.util.Optional;

@Component
public class PersonRepositoryImpl implements PersonRepository {
    private final PersonDao personDao;

    public PersonRepositoryImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    @Observed
    public Optional<Person> findById(SnowflakeId<Person> personId) {
        return Optional.ofNullable(PersonTable.convertToEntity(personDao.findById(personId.getValue())));
    }

    @Override
    @Observed
    public List<Person> findAll() {
        return PersonTable.convertToEntities(personDao.findAll());
    }

    @Override
    @Observed
    public Person save(Person person) {
        return PersonTable.convertToEntity(personDao.save(PersonTable.convertFrom(person)));
    }

    @Override
    @Observed
    public void deleteById(SnowflakeId<Person> id) {
        personDao.deleteById(id.getValue());

    }

}
