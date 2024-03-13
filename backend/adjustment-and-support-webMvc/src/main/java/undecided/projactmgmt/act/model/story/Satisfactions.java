package undecided.projactmgmt.act.model.story;

import lombok.Getter;

@Getter
public enum Satisfactions {
    UNDEFINED("未設定"),
    気に入る("気に入る"),
    当然である("当然である"),
    なんとも感じない("なんとも感じない"),
    しかたない("しかたない"),
    気に入らない("気に入らない");

    private final String displayName;

    Satisfactions(String displayName) {
        this.displayName = displayName;
        
    }

}
