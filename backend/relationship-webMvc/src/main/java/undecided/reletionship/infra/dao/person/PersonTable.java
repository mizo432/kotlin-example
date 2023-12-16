package undecided.reletionship.infra.dao.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import undecided.reletionship.model.party.person.Person;

import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "people", schema = "relationship")
public class PersonTable {
    @Id
    private Long personId;
    @Column(name = "PERSON_NAME", length = 50)
    private String name;

    public static List<Person> convertToEntities(List<PersonTable> list) {

        return list.stream().map(PersonTable::convertToEntity).toList();


    }

    public static Person convertToEntity(Optional<PersonTable> personTable) {
        if (personTable.isEmpty()) return null;
        PersonTable personTableP = personTable.get();
        return Person.reconstruct(personTableP.personId, personTableP.getName());


    }

    public static Person convertToEntity(PersonTable personTable) {
        if (personTable == null) return null;
        return Person.reconstruct(personTable.personId, personTable.getName());


    }

    public static PersonTable convertFrom(Person person) {
        PersonTable result = new PersonTable();
        result.setPersonId(person.personId().getValue());
        result.setName(person.name());

        return result;

    }
}
