package undecided.reletionship.infra.query.person;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import undecided.reletionship.model.party.person.Person;
import undecided.reletionship.model.party.person.PersonRepository;
import undecided.shared.entity.id.SnowflakeId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PersonQueryImplTest {
    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonQueryImpl personQueryImpl;

    /**
     * Method under test: {@link PersonQueryImpl#findAll()}
     */
    @Test
    void findAll() {
        assertTrue(personQueryImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link PersonQueryImpl#findById(SnowflakeId)}
     */
    @Test
    void findById() {
        Person person = new Person(new SnowflakeId<>(), "Name");

        Optional<Person> ofResult = Optional.of(person);
        when(personRepository.findById(Mockito.<SnowflakeId<Person>>any())).thenReturn(ofResult);
        Person actualFindByIdResult = personQueryImpl.findById(new SnowflakeId<>());
        verify(personRepository).findById(Mockito.<SnowflakeId<Person>>any());
        assertSame(person, actualFindByIdResult);
    }
}
