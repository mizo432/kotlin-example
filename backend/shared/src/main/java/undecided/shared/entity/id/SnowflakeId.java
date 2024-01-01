package undecided.shared.entity.id;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;
import undecided.common.entity.generator.NodeIdGenerator;
import undecided.common.entity.generator.SnowflakeIdGenerator;
import undecided.common.precondition.ObjectPreconditions;

import java.io.Serializable;
import java.util.Objects;

import static org.apache.logging.log4j.ThreadContext.isEmpty;


/**
 * スノーフレークID.
 */
@Embeddable
public class SnowflakeId implements Serializable {

    public static final SnowflakeId EMPTY_VALUE = new SnowflakeId();

    private final Long value;

    /**
     * コンストラクター.
     */
    public SnowflakeId() {
        this.value = null;

    }

    public Long getValue() {
        return value;
    }

    /**
     * コンストラクター.
     *
     * @param value 値
     */
    /* default */
    SnowflakeId(final Long value) {
        this.value = value;

    }

    public String asString() {
        if (isEmpty()) {
            return null;
        }
        return String.valueOf(getValue());
    }

    public static SnowflakeId newInstance() {
        return new SnowflakeId(
                new SnowflakeIdGenerator(NodeIdGenerator.generateNodeId()).generateID());

    }

    /**
     * データベースから再生成する場合のファクトリーメソッド
     *
     * @param value データベースから取得した値
     * @return 識別子オブジェクト
     */
    public static SnowflakeId reconstruct(final Long value) {
        return new SnowflakeId(value);

    }

    public static SnowflakeId of(@Nonnull final Long value) {
        ObjectPreconditions.checkNotNull(value, () -> new IllegalArgumentException("引数 valueにnullを設定できません。"));
        return new SnowflakeId(value);

    }

    @Override
    public String toString() {
        if (value == null)
            return null;
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnowflakeId that = (SnowflakeId) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SnowflakeId.class, value);

    }
}
