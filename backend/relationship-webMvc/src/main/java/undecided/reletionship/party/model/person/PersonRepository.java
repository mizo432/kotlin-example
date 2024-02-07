package undecided.reletionship.party.model.person;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Observed
public interface PersonRepository extends JpaRepository<Person, Long> {

}
