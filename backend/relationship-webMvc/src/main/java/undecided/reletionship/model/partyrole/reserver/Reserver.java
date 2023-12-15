package undecided.reletionship.model.partyrole.reserver;

import undecided.reletionship.model.party.ConcreteParty;
import undecided.reletionship.model.party.Party;
import undecided.reletionship.model.partyrole.PartyRole;

record Reserver(Party<ConcreteParty> party) implements PartyRole<ConcreteParty> {
    public static Reserver create(Party<ConcreteParty> party) {
        return new Reserver(party);

    }

}
