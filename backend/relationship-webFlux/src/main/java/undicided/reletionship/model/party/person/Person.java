package undicided.reletionship.model.party.person;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import undecided.shared.entity.id.SnowflakeId;

import java.time.LocalDate;

@Table(name = "people", schema = "relationship")
public record Person(
        @Id
        Long personId,
        @Column("PERSON_NAME")
        String name,

        @Column("DATE_OF_BIRTH")
        LocalDate dateOfBirth

) {

    public static Person createAtInsert(String name, LocalDate dateOfBirth) {
        return new Person(SnowflakeId.newInstance().getValue(), name, dateOfBirth);
    }

    public static Person createAtUpdate(Long personId, String name, LocalDate dateOfBirth) {
        return new Person(SnowflakeId.of(personId).getValue(), name, dateOfBirth);
    }

    public Person update(Person person) {
        return new Person(personId, person.name(), person.dateOfBirth);

    }
}
