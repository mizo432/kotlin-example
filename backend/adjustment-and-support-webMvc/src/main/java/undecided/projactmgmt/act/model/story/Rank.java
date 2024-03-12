package undecided.projactmgmt.act.model.story;

import lombok.Getter;
import undecided.common.taple.Pair;

import java.util.Map;

@Getter
public enum Rank {
    MUST_HAVE("必須", 99),
    REVERSE("逆効果", 0),
    LINEAR("線形", 33),
    QUESTIONABLE("懐疑的回答", null),
    EXCITER("魅力的", 66),
    INDIFFERENT("無関心", 0),
    EMPTY("未設定", null);

    private final String displayName;
    private final Integer businessValue;

    public Integer getValue() {
        return getBusinessValue();

    }

    Rank(String displayName, Integer businessValue) {
        this.displayName = displayName;
        this.businessValue = businessValue;
    }


    private static final Map<Pair<Satisfactions, Satisfactions>, Rank> rankBySatisfactionsPair = Map.ofEntries(
            Map.entry(new Pair<>(Satisfactions.UNDEFINED, Satisfactions.UNDEFINED), Rank.EMPTY),
            Map.entry(new Pair<>(Satisfactions.気に入る, Satisfactions.気に入る), Rank.QUESTIONABLE),
            Map.entry(new Pair<>(Satisfactions.気に入る, Satisfactions.当然である), Rank.EXCITER),
            Map.entry(new Pair<>(Satisfactions.気に入る, Satisfactions.なんとも感じない), Rank.EXCITER),
            Map.entry(new Pair<>(Satisfactions.気に入る, Satisfactions.しかたない), Rank.EXCITER),
            Map.entry(new Pair<>(Satisfactions.気に入る, Satisfactions.気に入らない), Rank.LINEAR),
            Map.entry(new Pair<>(Satisfactions.当然である, Satisfactions.気に入る), Rank.REVERSE),
            Map.entry(new Pair<>(Satisfactions.当然である, Satisfactions.当然である), Rank.INDIFFERENT),
            Map.entry(new Pair<>(Satisfactions.当然である, Satisfactions.なんとも感じない), Rank.INDIFFERENT),
            Map.entry(new Pair<>(Satisfactions.当然である, Satisfactions.しかたない), Rank.INDIFFERENT),
            Map.entry(new Pair<>(Satisfactions.当然である, Satisfactions.気に入らない), Rank.MUST_HAVE),
            Map.entry(new Pair<>(Satisfactions.なんとも感じない, Satisfactions.気に入る), Rank.REVERSE),
            Map.entry(new Pair<>(Satisfactions.なんとも感じない, Satisfactions.当然である), Rank.INDIFFERENT),
            Map.entry(new Pair<>(Satisfactions.なんとも感じない, Satisfactions.なんとも感じない), Rank.INDIFFERENT),
            Map.entry(new Pair<>(Satisfactions.なんとも感じない, Satisfactions.しかたない), Rank.INDIFFERENT),
            Map.entry(new Pair<>(Satisfactions.なんとも感じない, Satisfactions.気に入らない), Rank.MUST_HAVE),
            Map.entry(new Pair<>(Satisfactions.しかたない, Satisfactions.気に入る), Rank.REVERSE),
            Map.entry(new Pair<>(Satisfactions.しかたない, Satisfactions.当然である), Rank.INDIFFERENT),
            Map.entry(new Pair<>(Satisfactions.しかたない, Satisfactions.なんとも感じない), Rank.INDIFFERENT),
            Map.entry(new Pair<>(Satisfactions.しかたない, Satisfactions.しかたない), Rank.INDIFFERENT),
            Map.entry(new Pair<>(Satisfactions.しかたない, Satisfactions.気に入らない), Rank.MUST_HAVE),
            Map.entry(new Pair<>(Satisfactions.気に入らない, Satisfactions.気に入る), Rank.REVERSE),
            Map.entry(new Pair<>(Satisfactions.気に入らない, Satisfactions.当然である), Rank.REVERSE),
            Map.entry(new Pair<>(Satisfactions.気に入らない, Satisfactions.なんとも感じない), Rank.REVERSE),
            Map.entry(new Pair<>(Satisfactions.気に入らない, Satisfactions.しかたない), Rank.REVERSE),
            Map.entry(new Pair<>(Satisfactions.気に入らない, Satisfactions.気に入らない), Rank.QUESTIONABLE)
    );


    public static Rank calculateFrom(Satisfactions fullyImplementedSatisfactions, Satisfactions notImplementedSatisfactions) {
        return rankBySatisfactionsPair.getOrDefault(new Pair<>(fullyImplementedSatisfactions, notImplementedSatisfactions), Rank.EMPTY);
    }
}
