package undecided.reletionship.model.party.person;

import org.jetbrains.annotations.NotNull;
import undecided.reletionship.model.party.Party;
import undecided.shared.entity.id.SnowflakeId;

public record Person(
        SnowflakeId<Person> personId,
        String name

) implements Party<Person> {

    public static Person createAtInsert(@NotNull String name) {
        return new Person(SnowflakeId.newInstance(), name);
    }

    public static Person createAtUpdate(SnowflakeId<Person> personId, String name) {
        return new Person(personId, name);
    }

    public static Person reconstruct(Long personId, String name) {
        return new Person(SnowflakeId.reconstruct(personId), name);
    }

    public Person update(Person person) {
        return new Person(personId, person.name);
    }
}
