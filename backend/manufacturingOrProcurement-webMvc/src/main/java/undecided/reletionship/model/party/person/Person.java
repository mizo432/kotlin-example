package undecided.reletionship.model.party.person;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@jakarta.persistence.Table(name = "people", schema = "relationship")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @jakarta.persistence.Id
    @jakarta.persistence.Column(name = "person_id", nullable = false)
    private Long id;

    @jakarta.persistence.Column(name = "first_name", length = 50)
    private String firstName;

    @jakarta.persistence.Column(name = "last_name", length = 50)
    private String lastName;

    @jakarta.persistence.Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

}