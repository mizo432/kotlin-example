package undecided.reletionship.model.partyrole.customer;

import undecided.reletionship.model.party.ConcreteParty;
import undecided.reletionship.model.party.Party;
import undecided.reletionship.model.partyrole.PartyRole;

record Customer(Party<ConcreteParty> party) implements PartyRole<ConcreteParty> {

    public static Customer create(Party<ConcreteParty> party) {
        return new Customer(party);

    }
}
