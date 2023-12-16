package undecided.reletionship.infra.dao.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<PersonTable, Long> {
}
