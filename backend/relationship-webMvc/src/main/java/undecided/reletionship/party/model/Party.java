package undecided.reletionship.party.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

/**
 * パーティー
 * <pre>
 *     パーティーとは人または組織を指します
 * </pre>
 */
@Entity
@Table(schema = "relationship", name = "party")
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Party {
    @Id
    @Column(name = "party_id")
    private Long id;
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
        return getId() != null && Objects.equals(getId(), party.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public static class Parties {
        List<Party> value = new ArrayList<>();

        public Parties(Collection<Party> value) {
            this.value.addAll(value);
        }

        public static Parties of(Collection<Party> value) {
            return new Parties(value);
        }

        public Parties customerPeople() {
            return includeByType(PartyType.PERSON);
        }

        Parties includeByType(PartyType partyType) {
            List<Party> resultList = new ArrayList<>();
            for (Party party : value) {
                if (party.type.equals(partyType))
                    resultList.add(party);

            }
            return Parties.of(resultList);
        }

        public Parties customerOrganizationUnits() {
            return includeByType(PartyType.ORGANIZATION_UNIT);

        }

        public Set<Long> ids() {
            Set<Long> result = new HashSet<>();
            for (Party party : value) {
                result.add(party.getId());

            }

            return result;
        }
    }
}
