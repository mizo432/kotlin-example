package undecided.projactmgmt.act.model.story;

import lombok.Getter;

import java.util.EnumSet;

@Getter
public enum StoryStatus {
    SUGGESTED("sandbox", 1),
    ACCEPTED("backlog", 2),
    ESTIMATED("backlog", 3),
    PLANNED("sprint", 4),
    IN_PROGRESS("sprint", 5),
    IN_REVIEW("sprint", 6),
    DONE("sprint", 7),
    FROZEN("backlog", 8);


    private final String tabName;
    private final int id;

    StoryStatus(String tabName, int id) {
        this.tabName = tabName;
        this.id = id;
    }

    public static StoryStatus initial() {
        return StoryStatus.SUGGESTED;

    }

    public boolean canEstimate() {
        return EnumSet.of(StoryStatus.ESTIMATED, StoryStatus.ACCEPTED).contains(this);
    }
}
