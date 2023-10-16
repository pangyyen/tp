package seedu.cc.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NricTest {

    @Test
    void isValidNric() {
        // valid NRIC numbers
        assertTrue(Nric.isValidNric("12345678"));
        assertTrue(Nric.isValidNric("87654321"));

        // invalid NRIC numbers
        assertFalse(Nric.isValidNric("1234"));
        assertFalse(Nric.isValidNric("12345678901234567"));
        assertFalse(Nric.isValidNric("abcdefg"));
        assertFalse(Nric.isValidNric("1234567a"));
        assertFalse(Nric.isValidNric(""));
        assertFalse(Nric.isValidNric(" "));
    }

    @Test
    void testToString() {
        assertEquals("12345678", new Nric("12345678").toString());
        assertEquals("87654321", new Nric("87654321").toString());
    }

    @Test
    void testEquals() {
        // equal Nric objects
        assertEquals(new Nric("12345678"), new Nric("12345678"));
        assertEquals(new Nric("87654321"), new Nric("87654321"));

        // unequal Nric objects
        assertNotEquals(new Nric("12345678"), new Nric("87654321"));
        assertNotEquals(new Nric("87654321"), new Nric("12345678"));

        // comparing with null
        assertNotEquals(new Nric("12345678"), null);
    }

    @Test
    void testHashCode() {
        // equal hash codes for equal objects
        assertEquals(new Nric("12345678").hashCode(), new Nric("12345678").hashCode());
        assertEquals(new Nric("87654321").hashCode(), new Nric("87654321").hashCode());

        // unequal hash codes for unequal objects
        assertNotEquals(new Nric("12345678").hashCode(), new Nric("87654321").hashCode());
        assertNotEquals(new Nric("87654321").hashCode(), new Nric("12345678").hashCode());
    }
}

