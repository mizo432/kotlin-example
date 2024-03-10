package undecided.projactmgmt.act.model.story;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTest {
    /**
     * Method under test: {@link Rank#calculateFrom(Satisfactions, Satisfactions)}
     */
    @Test
    void calculateFrom() {
        // Arrange, Act and Assert
        assertEquals(Rank.EMPTY, Rank.calculateFrom(Satisfactions.UNDEFINED, Satisfactions.UNDEFINED));
        assertEquals(Rank.EMPTY, Rank.calculateFrom(Satisfactions.気に入る, Satisfactions.UNDEFINED));
        assertEquals(Rank.EMPTY, Rank.calculateFrom(Satisfactions.当然である, Satisfactions.UNDEFINED));
        assertEquals(Rank.EMPTY, Rank.calculateFrom(Satisfactions.なんとも感じない, Satisfactions.UNDEFINED));
        assertEquals(Rank.EMPTY, Rank.calculateFrom(Satisfactions.しかたない, Satisfactions.UNDEFINED));
        assertEquals(Rank.QUESTIONABLE, Rank.calculateFrom(Satisfactions.気に入る, Satisfactions.気に入る));
        assertEquals(Rank.EXCITER, Rank.calculateFrom(Satisfactions.気に入る, Satisfactions.当然である));
        assertEquals(Rank.REVERSE, Rank.calculateFrom(Satisfactions.当然である, Satisfactions.気に入る));
        assertEquals(Rank.INDIFFERENT, Rank.calculateFrom(Satisfactions.当然である, Satisfactions.当然である));
        assertEquals(Rank.REVERSE, Rank.calculateFrom(Satisfactions.なんとも感じない, Satisfactions.気に入る));
        assertEquals(Rank.REVERSE, Rank.calculateFrom(Satisfactions.しかたない, Satisfactions.気に入る));
        assertEquals(Rank.EMPTY, Rank.calculateFrom(Satisfactions.気に入らない, Satisfactions.UNDEFINED));
        assertEquals(Rank.REVERSE, Rank.calculateFrom(Satisfactions.気に入らない, Satisfactions.気に入る));
        assertEquals(Rank.LINEAR, Rank.calculateFrom(Satisfactions.気に入る, Satisfactions.気に入らない));
        assertEquals(Rank.MUST_HAVE, Rank.calculateFrom(Satisfactions.当然である, Satisfactions.気に入らない));
        assertEquals(Rank.QUESTIONABLE, Rank.calculateFrom(Satisfactions.気に入らない, Satisfactions.気に入らない));
    }

    /**
     * Method under test: {@link Rank#getValue()}
     */
    @Test
    void getValue() {

        // Arrange, Act and Assert
        assertEquals(99, Rank.MUST_HAVE.getValue().intValue());
    }
}
