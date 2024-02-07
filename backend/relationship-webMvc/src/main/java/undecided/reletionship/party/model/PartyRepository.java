package undecided.reletionship.party.model;

import org.springframework.data.jpa.repository.JpaRepository;
import undecided.shared.entity.id.SnowflakeId;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, SnowflakeId> {

    List<Party> findBySearchName(String searchName);

}