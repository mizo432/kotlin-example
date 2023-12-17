package undicided.reletionship.model.party.person;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import undecided.shared.entity.id.SnowflakeId;

@Table(name = "people", schema = "relationship")
public record Person(
        @Id
        Long personId,
        @Column("PERSON_NAME")
        String name

) {

    public static Person createAtInsert(String name) {
        return new Person(SnowflakeId.newInstance().getValue(), name);
    }

    public static Person createAtUpdate(Long personId, String name) {
        return new Person(SnowflakeId.of(personId).getValue(), name);
    }

    public Person update(Person person) {
        return new Person(personId, person.name());
    }
}
