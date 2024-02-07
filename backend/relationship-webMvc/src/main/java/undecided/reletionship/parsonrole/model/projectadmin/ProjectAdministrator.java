package undecided.reletionship.parsonrole.model.projectadmin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Entity
@Table(schema = "relationship", name = "PROJECT_ADMINISTRATOR")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProjectAdministrator {
    @Id
    @Column(name = "project_administrator_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long projectAdministratorId;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ProjectAdministrator that = (ProjectAdministrator) o;
        return getProjectAdministratorId() != null && Objects.equals(getProjectAdministratorId(), that.getProjectAdministratorId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
