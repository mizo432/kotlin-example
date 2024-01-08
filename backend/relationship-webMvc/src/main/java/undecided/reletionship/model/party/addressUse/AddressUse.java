package undecided.reletionship.model.party.addressUse;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address_use", schema = "relationship")
public class AddressUse {
    @EmbeddedId
    private AddressUseId id;

}