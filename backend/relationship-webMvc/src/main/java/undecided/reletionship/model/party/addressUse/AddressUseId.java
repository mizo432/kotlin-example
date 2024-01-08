package undecided.reletionship.model.party.addressUse;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AddressUseId implements Serializable {
    @Serial
    private static final long serialVersionUID = 4870631867536844071L;
    @jakarta.validation.constraints.NotNull
    @Column(name = "party_address_id", nullable = false)
    private Long partyAddressId;

    @jakarta.validation.constraints.NotNull
    @Column(name = "party_id", nullable = false)
    private Long partyId;

    @jakarta.validation.constraints.NotNull
    @Column(name = "address_use_type", nullable = false)
    private AddressUseType addressUseType;

    @jakarta.validation.constraints.NotNull
    @Column(name = "effective_date", nullable = false)
    private LocalDate effectiveDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AddressUseId entity = (AddressUseId) o;
        return Objects.equals(this.addressUseType, entity.addressUseType) &&
                Objects.equals(this.partyAddressId, entity.partyAddressId) &&
                Objects.equals(this.partyId, entity.partyId) &&
                Objects.equals(this.effectiveDate, entity.effectiveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressUseType, partyAddressId, partyId, effectiveDate);
    }

}