package undecided.projactmgmt.act.model.story;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "approve_stories", schema = "adjustment")
public class ApproveStory {
    @Id
    @Column(name = "approve_story_id", nullable = false)
    private Long id;

    @Column(name = "story_id")
    private Long storyId;

    @Column(name = "occurred_at")
    private LocalDateTime occurredAt;

}