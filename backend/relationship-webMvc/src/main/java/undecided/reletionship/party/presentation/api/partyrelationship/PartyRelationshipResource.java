package undecided.reletionship.party.presentation.api.partyrelationship;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import undecided.reletionship.party.business.command.partyrelationship.PartyRelationshipCommand;
import undecided.reletionship.party.business.query.partyrelationship.PartyRelationshipQuery;
import undecided.reletionship.party.model.partyrelationship.PartyRelationship;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/party/api/v1/party-relationship")
@RestController
public class PartyRelationshipResource {
    private final PartyRelationshipQuery partyRelationshipQuery;
    private final PartyRelationshipCommand partyRelationshipCommand;

    @GetMapping
    List<PartyRelationship> findAll() {
        return partyRelationshipQuery.findAll();


    }

    @GetMapping("/{Id}")
    PartyRelationship findById(Long id) {
        return partyRelationshipQuery.findOneById(id);

    }

    @PostMapping
    PartyRelationship post(@RequestBody PartyRelationship partyRelationship) {
        return partyRelationshipCommand.insert(partyRelationship);

    }

    @PutMapping("/{id}")
    PartyRelationship post(@PathVariable("id") Long id, @RequestBody PartyRelationship partyRelationship) {
        return partyRelationshipCommand.update(id, partyRelationship);

    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id) {
        partyRelationshipCommand.delete(id);

    }
}
