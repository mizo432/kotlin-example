package undecided.reletionship.presentation.api.party.person;

import undecided.reletionship.model.party.person.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record PersonDto(Long personId, String firstName, String lastName, LocalDate dateOfBirth) {

    public static PersonDto convertFrom(Person person) {
        return new PersonDto(person.getPersonId(), person.getFirstName(), person.getLastName(), person.getDateOfBirth());
    }

    public static List<PersonDto> convertFrom(List<Person> people) {
        return people.stream().map(PersonDto::convertFrom).collect(Collectors.toList());

    }

    public static PersonDto reconstruct(Person person) {
        return new PersonDto(person.getPersonId(), person.getFirstName(), person.getLastName(), person.getDateOfBirth());

    }

    public static List<PersonDto> reconstruct(List<Person> people) {
        ArrayList<PersonDto> result = new ArrayList<>();
        for (Person person : people) {
            result.add(PersonDto.reconstruct(person));
        }
        return result;

    }

    public Person convertToEntityAtInsert() {
        return Person.createAtInsert(firstName, lastName, dateOfBirth);

    }

    public Person convertToEntityAtUpdate() {
        return Person.createAtUpdate(personId, firstName, lastName, dateOfBirth);

    }
}
