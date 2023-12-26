package undecided.reletionship.model.partyrole.reserver;


import undecided.reletionship.model.party.ConcreteParty;

public record Reserver(ConcreteParty party) {
    public static Reserver create(ConcreteParty party) {
        return new Reserver(party);

    }

}
