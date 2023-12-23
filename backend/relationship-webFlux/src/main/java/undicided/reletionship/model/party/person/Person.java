package undicided.reletionship.model.party.person;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import undecided.shared.entity.id.SnowflakeId;

import java.time.LocalDate;

@Table(name = "people", schema = "relationship")
public record Person(
        @Id
        @Column("PERSON_ID")
        Long personId,
        @Column("FIRST_NAME")
        String firstName,
        @Column("LAST_NAME")
        String lastName,
        @Column("DATE_OF_BIRTH")
        LocalDate dateOfBirth

) {

    public static Person createAtInsert(String firstName, String lastName, LocalDate dateOfBirth) {
        return new Person(SnowflakeId.newInstance().getValue(), firstName, lastName, dateOfBirth);
    }

    public static Person createAtUpdate(Long personId, String firstName, String lastName, LocalDate dateOfBirth) {
        return new Person(SnowflakeId.of(personId).getValue(), firstName, lastName, dateOfBirth);
    }

    public Person update(Person person) {
        return new Person(personId, person.firstName(), person.lastName(), person.dateOfBirth);

    }
}
