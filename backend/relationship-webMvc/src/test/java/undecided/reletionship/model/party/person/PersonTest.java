package undecided.reletionship.model.party.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {
    /**
     * Method under test: {@link Person#createAtInsert(String)}
     */
    @Test
    void testCreate() {
        Person actual = Person.createAtInsert("Name");

        assertEquals(actual.name(), "Name");
    }

}
