package undecided.reletionship.model.party;

import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Table(schema = "relationship", name = "PARTY")
public record ConcreteParty(
        @Id
        Long partyId,

        PartyType type,
        String legalName,
        String searchName) {
}
