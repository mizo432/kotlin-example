package undecided.reletionship.presentation.api.party.person;

import undecided.reletionship.model.party.person.Person;
import undecided.shared.entity.id.SnowflakeId;

import java.util.List;
import java.util.stream.Collectors;

public record PersonDto(Long personId, String name) {

    public static PersonDto convertFrom(Person person) {
        return new PersonDto(person.personId().getValue(), person.name());
    }

    public static List<PersonDto> convertFrom(List<Person> people) {
        return people.stream().map(PersonDto::convertFrom).collect(Collectors.toList());

    }


    public Person convertToEntityAtInsert() {
        return Person.createAtInsert(name);

    }

    public Person convertToEntityAtUpdate() {
        return Person.createAtUpdate(SnowflakeId.of(personId), name);

    }
}
