package undecided.reletionship.business.query.person;

import org.springframework.stereotype.Service;
import undecided.reletionship.model.party.person.Person;

import java.util.List;

@Service
public interface PersonQuery {
    List<Person> findAll();

    Person findById(Long id);

}
