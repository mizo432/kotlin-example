package undecided.reletionship.party.model.addressUse;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "address_use", schema = "relationship")
public class AddressUse {
    @Id
    @Column(name = "party_address_id")
    private Long id;
    @Column(name = "address_use_type")
    @Enumerated(EnumType.STRING)
    private AddressUseType type;
    private Long partyId;
    private Long addressId;


    public static class AddressUseList {
        List<AddressUse> value = new ArrayList<>();

        private AddressUseList(Collection<AddressUse> addressUseCollection) {
            value.addAll(addressUseCollection);

        }

        public static AddressUseList of(Collection<AddressUse> addressUseCollection) {
            return new AddressUseList(addressUseCollection);

        }

        public AddressUse currentAddress() {
            return value.stream()
                    .filter(addressUse -> EnumSet.of(AddressUseType.ADDRESS, AddressUseType.ADDRESS2).contains(addressUse.getType()))
                    .findFirst()
                    .orElseThrow(() -> new AddressUseNotFoundException("該当のアドレスがありません。"));
        }
    }

}