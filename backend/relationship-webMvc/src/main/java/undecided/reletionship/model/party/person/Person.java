package undecided.reletionship.model.party.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import undecided.shared.entity.id.SnowflakeId;

import java.time.LocalDate;

@Entity
@Table(name = "people", schema = "relationship")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @Column(name = "PERSON_ID")
    private Long personId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    public static Person createAtInsert(String firstName, String lastName, LocalDate dateOfBirth) {
        return new Person(SnowflakeId.newInstance().getValue(), firstName, lastName, dateOfBirth);
    }

    public static Person createAtUpdate(Long personId, String firstName, String lastName, LocalDate dateOfBirth) {
        return new Person(SnowflakeId.of(personId).getValue(), firstName, lastName, dateOfBirth);
    }

    public Person update(Person person) {
        return new Person(personId, person.getFirstName(), person.getLastName(), person.getDateOfBirth());

    }
}
