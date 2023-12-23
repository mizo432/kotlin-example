package undicided.reletionship.presentation.api.party.person;

import undicided.reletionship.model.party.person.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record PersonDto(Long personId, String firstName, String lastName, LocalDate dateOfBirth) {

    public static PersonDto convertFrom(Person person) {
        return new PersonDto(person.personId(), person.firstName(), person.lastName(), person.dateOfBirth());
    }

    public static List<PersonDto> convertFrom(List<Person> people) {
        return people.stream().map(PersonDto::convertFrom).collect(Collectors.toList());

    }


    public Person convertToEntityAtInsert() {
        return Person.createAtInsert(firstName, lastName, dateOfBirth);

    }

    public Person convertToEntityAtUpdate() {
        return Person.createAtUpdate(personId, firstName, lastName, dateOfBirth);

    }
}
