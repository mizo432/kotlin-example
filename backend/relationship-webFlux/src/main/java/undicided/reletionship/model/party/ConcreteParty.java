package undicided.reletionship.model.party;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(schema = "relationship", name = "PARTY")
public record ConcreteParty(
        @Id
        Long partyId,

        PartyType type) {
}
