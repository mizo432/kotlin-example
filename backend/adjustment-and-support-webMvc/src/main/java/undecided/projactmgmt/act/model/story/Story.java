package undecided.projactmgmt.act.model.story;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(schema = "adjustment", name = "stories")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Story {
    @Id
    @Column(name = "story_id")
    private Long id;
    @Column(nullable = false)
    private Long projectId;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(length = 3000)
    String notes;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoryStatus state;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoryType type;
    //minimum: 1
    private Integer rank;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Satisfactions fullyImplementedSatisfactions;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Satisfactions notImplementedSatisfactions;
    private Long featureId;
    private Long dependsOnStoryId;
    private Long creatorId;
    private Long parentSprintId;
    //    minimum: 0
//    maximum: 99
    private Integer effort;
    private String affectVersion;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Story story = (Story) o;
        return getId() != null && Objects.equals(getId(), story.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
