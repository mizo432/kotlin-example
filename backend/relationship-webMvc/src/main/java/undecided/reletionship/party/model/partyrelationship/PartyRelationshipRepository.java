package undecided.reletionship.party.model.partyrelationship;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PartyRelationshipRepository extends JpaRepository<PartyRelationship, Long> {

    List<PartyRelationship> findFOtPartyByFromIdAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndType(Long partyId, LocalDate startDate, LocalDate endDate, RelationshipType type);

    default List<PartyRelationship> findByTargetDateAndType(Long partyId, LocalDate givenDate, RelationshipType type) {
        return findFOtPartyByFromIdAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndType(partyId, givenDate, givenDate, type);

    }
}
