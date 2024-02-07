package undecided.reletionship.party.infra.query.partyrelationship;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import undecided.reletionship.party.business.query.partyrelationship.PartyRelationshipQuery;
import undecided.reletionship.party.model.partyrelationship.PartyRelationship;
import undecided.reletionship.party.model.partyrelationship.PartyRelationshipRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PartyRelationshipQueryImpl implements PartyRelationshipQuery {
    private final PartyRelationshipRepository partyRelationshipRepository;

    @Override
    public List<PartyRelationship> findAll() {
        return partyRelationshipRepository.findAll();

    }

    @Override
    public PartyRelationship findOneById(Long id) {
        return partyRelationshipRepository.findById(id).orElseThrow();

    }
}
