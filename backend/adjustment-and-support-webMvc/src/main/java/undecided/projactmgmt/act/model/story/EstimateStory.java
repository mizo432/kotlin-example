package undecided.projactmgmt.act.model.story;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "estimate_stories", schema = "adjustment")
public class EstimateStory {
    @Id
    @Column(name = "estimate_story_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "story_id", nullable = false)
    private Long storyId;

    @NotNull
    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    @NotNull
    @Column(name = "effort", nullable = false)
    private Integer effort;

}