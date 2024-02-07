package undecided.reletionship.party.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import undecided.shared.entity.id.SnowflakeId;

import java.util.Objects;

/**
 * パーティー
 * <pre>
 *     パーティーとは人または組織を指します
 * </pre>
 */
@Entity
@Table(schema = "relationship", name = "PARTY")
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Party {
    @Id
    private SnowflakeId partyId;
    private PartyType type;
    private String legalName;
    private String searchName;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Party party = (Party) o;
        return getPartyId() != null && Objects.equals(getPartyId(), party.getPartyId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
