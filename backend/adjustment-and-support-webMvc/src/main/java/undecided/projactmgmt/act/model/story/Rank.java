package undecided.projactmgmt.act.model.story;

import lombok.Getter;

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

    public static Rank calculateFrom(Satisfactions fullyImplementedSatisfactions, Satisfactions notImplementedSatisfactions) {
        if (fullyImplementedSatisfactions == Satisfactions.UNDEFINED || notImplementedSatisfactions == Satisfactions.UNDEFINED)
            return Rank.EMPTY;
        if (fullyImplementedSatisfactions == Satisfactions.気に入る) {
            switch (notImplementedSatisfactions) {
                case 気に入る -> {
                    return Rank.QUESTIONABLE;
                }
                case 当然である, なんとも感じない, しかたない -> {
                    return Rank.EXCITER;
                }
                case 気に入らない -> {
                    return Rank.LINEAR;
                }
            }
        }
        if ((fullyImplementedSatisfactions == Satisfactions.当然である) || (fullyImplementedSatisfactions == Satisfactions.なんとも感じない) || (fullyImplementedSatisfactions == Satisfactions.しかたない)) {
            switch (notImplementedSatisfactions) {
                case 気に入る -> {
                    return Rank.REVERSE;
                }
                case 当然である, なんとも感じない, しかたない -> {
                    return Rank.INDIFFERENT;
                }
                case 気に入らない -> {
                    return Rank.MUST_HAVE;
                }
            }
        }
        if (fullyImplementedSatisfactions == Satisfactions.気に入らない) {
            switch (notImplementedSatisfactions) {
                case 気に入る, 当然である, なんとも感じない, しかたない -> {
                    return Rank.REVERSE;
                }
                case 気に入らない -> {
                    return Rank.QUESTIONABLE;
                }
            }
        }
        return Rank.EMPTY;
    }
}
