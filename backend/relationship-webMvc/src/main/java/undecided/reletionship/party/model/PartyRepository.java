package undecided.reletionship.party.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {

    List<Party> findBySearchName(String searchName);

}