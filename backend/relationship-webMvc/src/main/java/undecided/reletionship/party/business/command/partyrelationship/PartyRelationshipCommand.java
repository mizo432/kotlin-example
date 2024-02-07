package undecided.reletionship.party.business.command.partyrelationship;

import org.springframework.stereotype.Service;
import undecided.reletionship.party.model.partyrelationship.PartyRelationship;
import undecided.reletionship.party.model.partyrelationship.PartyRelationshipRepository;

@Service
public class PartyRelationshipCommand {
    private final PartyRelationshipRepository partyRelationshipRepository;

    public PartyRelationshipCommand(PartyRelationshipRepository partyRelationshipRepository) {
        this.partyRelationshipRepository = partyRelationshipRepository;
    }

    public PartyRelationship insert(PartyRelationship partyRelationship) {
        PartyRelationship newPartyRelationship = partyRelationship.insert();
        return partyRelationshipRepository.save(newPartyRelationship);

    }

    public PartyRelationship update(Long id, PartyRelationship partyRelationship) {
        PartyRelationship oldPartyRelationship = partyRelationshipRepository.findById(id).orElseThrow();
        PartyRelationship updatePartyRelationship = oldPartyRelationship.update(partyRelationship);
        return partyRelationshipRepository.save(updatePartyRelationship);

    }

    public void delete(Long id) {
        partyRelationshipRepository.deleteById(id);

    }
}
