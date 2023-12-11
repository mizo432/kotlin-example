package com.undecided.inventory.model.municipal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MunicipalIdentifierTest {
    /**
     * Method under test: {@link MunicipalId#of(String)}
     */
    @Test
    void testOf() {
        assertThrows(IllegalArgumentException.class, () -> MunicipalId.of("Code"));
        assertThrows(IllegalArgumentException.class, () -> MunicipalId.of("Pref Code", "Municipal Code"));
        assertThrows(IllegalArgumentException.class, () -> MunicipalId.of("42", "Municipal Code"));
    }

    /**
     * Method under test: {@link MunicipalId#of(String)}
     */
    @Test
    void testOf2() {
        MunicipalId actualOfResult = MunicipalId.of("42foo");
        assertEquals("42", actualOfResult.getPrefCode());
        assertEquals("foo", actualOfResult.getMunicipalCode());
    }

    /**
     * Method under test: {@link MunicipalId#of(String)}
     */
    @Test
    void testOf3() {
        MunicipalId actualOfResult = MunicipalId.of("424242");
        assertEquals("42", actualOfResult.getPrefCode());
        assertEquals("424", actualOfResult.getMunicipalCode());
    }

    /**
     * Method under test: {@link MunicipalId#of(String, String)}
     */
    @Test
    void testOf4() {
        MunicipalId actualOfResult = MunicipalId.of("42", "foo");
        assertEquals("42", actualOfResult.getPrefCode());
        assertEquals("foo", actualOfResult.getMunicipalCode());
    }

    /**
     * Method under test: {@link MunicipalId#of(String)}
     */
    @Test
    void testOf1() {
        assertThrows(IllegalArgumentException.class, () -> MunicipalId.of("424243"));
    }

    /**
     * Method under test: {@link MunicipalId#getCheckDigit()}
     */
    @Test
    void testGetCheckDigit() {
        assertEquals(2, MunicipalId.of("424242").getCheckDigit());
    }

    /**
     * Method under test: {@link MunicipalId#getCode()}
     */
    @Test
    void testGetCode() {
        assertEquals("424242", MunicipalId.of("424242").getCode());
    }

}
