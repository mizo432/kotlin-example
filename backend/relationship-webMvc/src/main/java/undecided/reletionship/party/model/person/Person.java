package undecided.reletionship.party.model.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import undecided.shared.entity.id.SnowflakeId;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "people", schema = "relationship")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Person person = (Person) o;
        return getPersonId() != null && Objects.equals(getPersonId(), person.getPersonId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
