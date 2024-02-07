package undecided.reletionship.party.business.query.person;

import org.springframework.stereotype.Service;
import undecided.reletionship.party.model.person.Person;

import java.util.List;

@Service
public interface PersonQuery {
    List<Person> findAll();

    Person findById(Long id);

}
