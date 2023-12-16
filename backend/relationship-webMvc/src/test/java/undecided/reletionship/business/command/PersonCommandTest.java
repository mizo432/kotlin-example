package undecided.reletionship.business.command;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;
import undecided.shared.entity.id.SnowflakeId;

@ContextConfiguration(classes = {PersonCommand.class})
@ExtendWith(SpringExtension.class)
class PersonCommandTest {
    @Autowired
    private PersonCommand personCommand;

    @MockBean
    private PersonRepository personRepository;

    /**
     * Method under test: {@link PersonCommand#insert(Person)}
     */
    @Test
    void insert() {
        Person person = new Person(new SnowflakeId<>(), "Name");

        when(personRepository.save(Mockito.<Person>any())).thenReturn(person);
        Person actualInsertResult = personCommand.insert(new Person(new SnowflakeId<>(), "Name"));
        verify(personRepository).save(Mockito.<Person>any());
        assertSame(person, actualInsertResult);
    }

    /**
     * Method under test: {@link PersonCommand#update(SnowflakeId, Person)}
     */
    @Test
    void update() {
        Person person = new Person(new SnowflakeId<>(), "Name");

        when(personRepository.save(Mockito.<Person>any())).thenReturn(person);
        Optional<Person> ofResult = Optional.of(new Person(new SnowflakeId<>(), "Name"));
        when(personRepository.findById(Mockito.<SnowflakeId<Person>>any())).thenReturn(ofResult);
        SnowflakeId<Person> id = new SnowflakeId<>();
        Person actualUpdateResult = personCommand.update(id, new Person(new SnowflakeId<>(), "Name"));
        verify(personRepository).findById(Mockito.<SnowflakeId<Person>>any());
        verify(personRepository).save(Mockito.<Person>any());
        assertSame(person, actualUpdateResult);
    }

    /**
     * Method under test: {@link PersonCommand#delete(SnowflakeId)}
     */
    @Test
    void delete() {
        doNothing().when(personRepository).deleteById(Mockito.<SnowflakeId<Person>>any());
        Optional<Person> ofResult = Optional.of(new Person(new SnowflakeId<>(), "Name"));
        when(personRepository.findById(Mockito.<SnowflakeId<Person>>any())).thenReturn(ofResult);
        personCommand.delete(new SnowflakeId<>());
        verify(personRepository).deleteById(Mockito.<SnowflakeId<Person>>any());
        verify(personRepository).findById(Mockito.<SnowflakeId<Person>>any());
    }
}
