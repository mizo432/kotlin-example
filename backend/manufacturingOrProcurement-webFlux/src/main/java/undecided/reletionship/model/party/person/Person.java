package undecided.reletionship.model.party.person;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "people", schema = "relationship")
@Entity
public class Person {
    @jakarta.persistence.Id
    @jakarta.persistence.Column(name = "person_id", nullable = false)
    private Long id;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.persistence.Column(name = "first_name", length = 50)
    private String firstName;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.persistence.Column(name = "last_name", length = 50)
    private String lastName;

    @jakarta.persistence.Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}