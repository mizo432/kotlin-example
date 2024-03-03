package undecided.projactmgmt.request.model.activityrequest;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

/**
 * プロジェクト活動リクエスト
 */
@Entity
@Table(schema = "adjustment", name = "project_activity_requests")
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityRequest {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "project_activity_request_type")
    private ActivityRequestType type;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "project_activity_request_status")
    private ActivityRequestStatus status;
    private Integer priority;
    @Column(nullable = false)
    private LocalDate requestDate;
    @Column(nullable = false)
    private LocalDate dueDate;
    private LocalDate approvalDate;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer estimatedDuration;
    @Column(nullable = false)
    private Integer estimatedCost;
    @Column(nullable = false)
    private Integer budget;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ActivityRequest that = (ActivityRequest) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
