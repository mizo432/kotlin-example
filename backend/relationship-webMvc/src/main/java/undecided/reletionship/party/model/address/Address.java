package undecided.reletionship.party.model.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import undecided.shared.entity.id.SnowflakeId;

import java.util.Objects;

@Entity
@Table(schema = "relationship", name = "address")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    private Long partyAddressId;

    private Long addressId;

    @Column(length = 2, nullable = false)
    private String prefectureCode;

    @Column(length = 50, nullable = false)
    private String prefectureName;

    @Column(length = 50, nullable = false)
    private String prefectureKanaName;

    @Column(length = 6)
    private String municipalCode;

    @Column(length = 50)
    private String gunName;

    @Column(length = 50)
    private String gunKanaName;

    @Column(length = 50)
    private String cityName;

    @Column(length = 50)
    private String cityKanaName;

    @Column(length = 50)
    private String ordinanceDesignatedCityName;

    @Column(length = 50)
    private String ordinanceDesignatedCityKanaName;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Address address = (Address) o;
        return getPartyAddressId() != null && Objects.equals(getPartyAddressId(), address.getPartyAddressId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public static Address create(Long addressId, String prefectureCode, String prefectureName, String prefectureKanaName, String municipalCode, String gunName, String gunKanaName, String cityName, String cityKanaName, String ordinanceDesignatedCityName, String ordinanceDesignatedCityKanaName) {
        return new Address(SnowflakeId.newInstance().getValue(), addressId, prefectureCode, prefectureName, prefectureKanaName, municipalCode, gunName, gunKanaName, cityName, cityKanaName, ordinanceDesignatedCityName, ordinanceDesignatedCityKanaName);

    }
}
