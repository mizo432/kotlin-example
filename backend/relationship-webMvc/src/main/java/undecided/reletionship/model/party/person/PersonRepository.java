package undecided.reletionship.model.party.person;

import org.springframework.stereotype.Component;
import undecided.shared.entity.id.SnowflakeId;

import java.util.List;
import java.util.Optional;

@Component
public interface PersonRepository {

    Optional<Person> findById(SnowflakeId<Person> personId);

    List<Person> findAll();

    Person save(Person person);

    void deleteById(SnowflakeId<Person> id);

}
