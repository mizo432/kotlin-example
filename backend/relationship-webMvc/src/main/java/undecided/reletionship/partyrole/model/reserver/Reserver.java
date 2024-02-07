package undecided.reletionship.partyrole.model.reserver;


import undecided.reletionship.party.model.Party;

public record Reserver(Party party) {
    public static Reserver create(Party party) {
        return new Reserver(party);

    }

}
