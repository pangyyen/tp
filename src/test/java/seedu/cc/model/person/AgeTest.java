package seedu.cc.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Age.isValidAddress(null));

        // invalid addresses
        assertFalse(Age.isValidAddress("")); // empty string
        assertFalse(Age.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Age.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Age.isValidAddress("-")); // one character
        assertTrue(Age.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        Age age = new Age("Valid Address");

        // same values -> returns true
        assertTrue(age.equals(new Age("Valid Address")));

        // same object -> returns true
        assertTrue(age.equals(age));

        // null -> returns false
        assertFalse(age.equals(null));

        // different types -> returns false
        assertFalse(age.equals(5.0f));

        // different values -> returns false
        assertFalse(age.equals(new Age("Other Valid Address")));
    }
}
