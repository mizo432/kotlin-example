package undicided.reletionship.model.partyrole.reserver;


import undicided.reletionship.model.party.ConcreteParty;

public record Reserver(ConcreteParty party) {
    public static Reserver create(ConcreteParty party) {
        return new Reserver(party);

    }

}
