package undecided.reletionship.model.party.addressUse;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    @EmbeddedId
    private AddressUseId id;

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
                    .filter(addressUse -> EnumSet.of(AddressUseType.ADDRESS, AddressUseType.ADDRESS2).contains(addressUse.getId().getAddressUseType()))
                    .findFirst()
                    .orElseThrow(() -> new AddressUseNotFoundException("該当のアドレスがありません。"));
        }
    }

}