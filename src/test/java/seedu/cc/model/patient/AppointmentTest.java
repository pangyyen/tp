package seedu.cc.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.cc.model.appointment.AppointmentEvent;

class AppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerEXception() {
        assertThrows(NullPointerException.class, () -> new AppointmentEvent(null, null));
        assertThrows(NullPointerException.class, () -> new AppointmentEvent(null, "12:00"));
        assertThrows(NullPointerException.class, () -> new AppointmentEvent("2023-12-01", null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        String invalidTime = "25:00";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentEvent("2023-12-01", invalidTime));
        assertThrows(IllegalArgumentException.class, () -> new AppointmentEvent(invalidDate, "12:00"));
    }

    @Test
    void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> AppointmentEvent.isValidDate(null));

        // invalid dates
        assertFalse(AppointmentEvent.isValidDate("")); // empty string
        assertFalse(AppointmentEvent.isValidDate("20231001")); // missing hyphens
        assertFalse(AppointmentEvent.isValidDate("01-10-2023")); // missing wrong format
        assertFalse(AppointmentEvent.isValidDate("2023-10-1")); // missing leading zeros

        // valid dates
        assertTrue(AppointmentEvent.isValidDate("2023-10-01"));
        assertTrue(AppointmentEvent.isValidDate("2023-12-31"));
        assertTrue(AppointmentEvent.isValidDate("2020-02-29")); // leap year
    }

    @Test
    void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> AppointmentEvent.isValidTime(null));

        // invalid times
        assertFalse(AppointmentEvent.isValidTime("")); // empty string
        assertFalse(AppointmentEvent.isValidTime("1200")); // missing colon
        assertFalse(AppointmentEvent.isValidTime("25:00")); // invalid hour
        assertFalse(AppointmentEvent.isValidTime("12:60")); // invalid minutes

        // valid times
        assertTrue(AppointmentEvent.isValidTime("00:00"));
        assertTrue(AppointmentEvent.isValidTime("23:59"));
    }

    @Test
    void testEquals() {
        AppointmentEvent appointmentEvent = new AppointmentEvent("2023-10-01", "12:00");

        // same values -> returns true
        assertEquals(appointmentEvent, new AppointmentEvent("2023-10-01", "12:00"));

        // same object -> returns true
        assertEquals(appointmentEvent, appointmentEvent);

        // null -> returns false
        assertNotEquals(null, appointmentEvent);

        // different types -> returns false
        assertFalse(appointmentEvent.equals(5.0f));

        // different values -> returns false
        assertNotEquals(appointmentEvent, new AppointmentEvent("2023-10-02", "12:00"));
        assertNotEquals(appointmentEvent, new AppointmentEvent("2023-10-01", "13:00"));
    }
}
