package undecided.shared.entity.id;

import org.junit.jupiter.api.Test;
import undecided.common.entity.Entity;

import static org.junit.jupiter.api.Assertions.*;

class SnowflakeIdTest {
    /**
     * Method under test: {@link SnowflakeId#asString()}
     */
    @Test
    void testAsString() {
        SnowflakeId<Entity> snowflakeId = new SnowflakeId<>();
        assertNull(snowflakeId.asString());
    }

    /**
     * Method under test: {@link SnowflakeId#newInstance()}
     */
    @Test
    void testNewInstance() {

        SnowflakeId<Entity> actual = SnowflakeId.newInstance();

        assertNotEquals(SnowflakeId.reconstruct(null), actual);
    }

    /**
     * Method under test: {@link SnowflakeId#reconstruct(Long)}
     */
    @Test
    void testReconstruct() {
        SnowflakeId<Entity> actualReconstructResult = SnowflakeId.reconstruct(42L);
        assertEquals(42L, actualReconstructResult.getValue().longValue());
    }

    /**
     * Method under test: {@link SnowflakeId#of(Long)}
     */
    @Test
    void testOf() {
        SnowflakeId<Entity> actualOfResult = SnowflakeId.of(42L);
        assertEquals(42L, actualOfResult.getValue().longValue());
    }

    /**
     * Method under test: {@link SnowflakeId#toString()}
     */
    @Test
    void testToString() {
        SnowflakeId<Entity> snowflakeId = new SnowflakeId<>();
        assertNull(snowflakeId.toString());
    }

    /**
     * Method under test: {@link SnowflakeId#toString()}
     */
    @Test
    void testToString2() {
        SnowflakeId<Entity> ofResult = SnowflakeId.of(42L);
        assertEquals("42", ofResult.toString());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SnowflakeId#SnowflakeId()}
     *   <li>{@link SnowflakeId#getValue()}
     * </ul>
     */
    @Test
    void testConstructor() {
        SnowflakeId<Entity> actualSnowflakeId = new SnowflakeId<>();
        assertNull(actualSnowflakeId.getValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SnowflakeId#SnowflakeId(Long)}
     *   <li>{@link SnowflakeId#getValue()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        SnowflakeId<Entity> actualSnowflakeId = new SnowflakeId<>(42L);
        assertEquals(42L, actualSnowflakeId.getValue().longValue());
    }

    /**
     * Method under test: {@link SnowflakeId#equals(Object)}
     */
    @Test
    void testEquals() {
        SnowflakeId<Entity> newInstanceResult = SnowflakeId.newInstance();
        assertNotEquals(newInstanceResult, null);
    }

    /**
     * Method under test: {@link SnowflakeId#equals(Object)}
     */
    @Test
    void testEquals2() {
        SnowflakeId<Entity> newInstanceResult = SnowflakeId.newInstance();
        assertNotEquals(newInstanceResult, "Different type to SnowflakeId");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SnowflakeId#equals(Object)}
     *   <li>{@link SnowflakeId#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        SnowflakeId<Entity> newInstanceResult = SnowflakeId.newInstance();
        assertEquals(newInstanceResult, newInstanceResult);
        int expectedHashCodeResult = newInstanceResult.hashCode();
        assertEquals(expectedHashCodeResult, newInstanceResult.hashCode());
    }

    /**
     * Method under test: {@link SnowflakeId#equals(Object)}
     */
    @Test
    void testEquals4() {
        SnowflakeId<Entity> newInstanceResult = SnowflakeId.newInstance();
        SnowflakeId<Entity> newInstanceResult2 = SnowflakeId.newInstance();
        assertNotEquals(newInstanceResult, newInstanceResult2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SnowflakeId#equals(Object)}
     *   <li>{@link SnowflakeId#hashCode()}
     * </ul>
     */
    @Test
    void testEquals5() {
        SnowflakeId<Entity> snowflakeId = new SnowflakeId<>();
        SnowflakeId<Entity> snowflakeId2 = new SnowflakeId<>();
        assertEquals(snowflakeId, snowflakeId2);
        int expectedHashCodeResult = snowflakeId.hashCode();
        assertEquals(expectedHashCodeResult, snowflakeId2.hashCode());
    }
}
