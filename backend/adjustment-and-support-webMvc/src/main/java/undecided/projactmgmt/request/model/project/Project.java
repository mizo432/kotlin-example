package undecided.projactmgmt.request.model.project;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(schema = "adjustment", name = "projects")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "project_type")
    private ProjectType type;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;
    private Integer priority;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private Integer estimatedDuration;
    private Integer estimatedCost;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Project project = (Project) o;
        return getId() != null && Objects.equals(getId(), project.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
