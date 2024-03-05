package undecided.projactmgmt.act.model.story;

import lombok.Getter;

@Getter
public enum StoryType {
    USER_STORY("ユーザーストーリー", 1),
    DEFECT("障害", 2),
    TECHNICAL_STORY("技術ストーリー", 3),
    EPIC_STORY("エピックストーリー", 4);

    private final String name;
    private final int id;

    StoryType(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
