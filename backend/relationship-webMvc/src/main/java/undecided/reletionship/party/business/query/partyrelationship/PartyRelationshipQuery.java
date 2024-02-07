package undecided.reletionship.party.business.query.partyrelationship;

import undecided.reletionship.party.model.partyrelationship.PartyRelationship;

import java.util.List;

public interface PartyRelationshipQuery {
    List<PartyRelationship> findAll();

    PartyRelationship findOneById(Long id);

}
