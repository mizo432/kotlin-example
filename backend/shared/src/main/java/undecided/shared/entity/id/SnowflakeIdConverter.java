package undecided.shared.entity.id;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SnowflakeIdConverter implements AttributeConverter<SnowflakeId, Long> {
    @Override
    public Long convertToDatabaseColumn(SnowflakeId attribute) {
        return attribute.getValue();
    }

    @Override
    public SnowflakeId<?> convertToEntityAttribute(Long dbData) {
        return SnowflakeId.reconstruct(dbData);
    }
}
