package seedu.cc.model.patient;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.cc.testutil.Assert.assertThrows;

class AppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerEXception() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null));
        assertThrows(NullPointerException.class, () -> new Appointment(null, "12:00"));
        assertThrows(NullPointerException.class, () -> new Appointment("2023-12-01", null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        String invalidTime = "25:00";
        assertThrows(IllegalArgumentException.class, () -> new Appointment("2023-12-01", invalidTime));
        assertThrows(IllegalArgumentException.class, () -> new Appointment(invalidDate, "12:00"));
    }

    @Test
    void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Appointment.isValidDate(null));

        // invalid dates
        assertFalse(Appointment.isValidDate("")); // empty string
        assertFalse(Appointment.isValidDate("20231001")); // missing hyphens
        assertFalse(Appointment.isValidDate("01-10-2023")); // missing wrong format
        assertFalse(Appointment.isValidDate("2023-10-1")); // missing leading zeros

        // valid dates
        assertTrue(Appointment.isValidDate("2023-10-01"));
        assertTrue(Appointment.isValidDate("2023-12-31"));
        assertTrue(Appointment.isValidDate("2020-02-29")); // leap year
    }

    @Test
    void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> Appointment.isValidTime(null));

        // invalid times
        assertFalse(Appointment.isValidTime("")); // empty string
        assertFalse(Appointment.isValidTime("1200")); // missing colon
        assertFalse(Appointment.isValidTime("25:00")); // invalid hour
        assertFalse(Appointment.isValidTime("12:60")); // invalid minutes

        // valid times
        assertTrue(Appointment.isValidTime("00:00"));
        assertTrue(Appointment.isValidTime("23:59"));
    }

    @Test
    void testEquals() {
        Appointment appointment = new Appointment("2023-10-01", "12:00");

        // same values -> returns true
        assertEquals(appointment, new Appointment("2023-10-01", "12:00"));

        // same object -> returns true
        assertEquals(appointment, appointment);

        // null -> returns false
        assertNotEquals(null, appointment);

        // different types -> returns false
        assertFalse(appointment.equals(5.0f));

        // different values -> returns false
        assertNotEquals(appointment, new Appointment("2023-10-02", "12:00"));
        assertNotEquals(appointment, new Appointment("2023-10-01", "13:00"));
    }
}