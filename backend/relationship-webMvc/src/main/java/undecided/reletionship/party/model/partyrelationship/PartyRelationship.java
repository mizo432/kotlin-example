package undecided.reletionship.party.model.partyrelationship;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import undecided.shared.entity.id.SnowflakeId;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(schema = "relationship", name = "party_relationship")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class PartyRelationship {

    @Id
    @Column(name = "party_relationship_id")
    private Long id;
    @Column(name = "from_id")
    private Long fromId;
    @Column(name = "to_id")
    private Long toId;
    @Column(name = "relationship_type")
    @Enumerated(EnumType.STRING)
    private RelationshipType type;
    private LocalDate startDate;
    private LocalDate endDate;


    public static PartyRelationship create(Long fromId, Long toId, RelationshipType type, LocalDate startDate, LocalDate endDate) {
        return new PartyRelationship(SnowflakeId.newInstance().getValue(),
                fromId,
                toId,
                type,
                startDate,
                endDate);

    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PartyRelationship that = (PartyRelationship) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();

    }

    public PartyRelationship update(PartyRelationship partyRelationship) {
        return new PartyRelationship(id,
                partyRelationship.getFromId(),
                partyRelationship.getToId(),
                partyRelationship.getType(),
                partyRelationship.getStartDate(),
                partyRelationship.getEndDate());

    }

    public PartyRelationship insert() {
        return new PartyRelationship(SnowflakeId.newInstance().getValue(),
                getFromId(),
                getToId(),
                getType(),
                getStartDate(),
                getEndDate());

    }
}
