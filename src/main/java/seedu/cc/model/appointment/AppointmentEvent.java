package seedu.cc.model.appointment;

import static java.util.Objects.requireNonNull;

import seedu.cc.model.util.Date;
import seedu.cc.model.util.Time;

/**
 * Represents a Patient's appointment in the clinic book.
 */
public class AppointmentEvent {
    public static final String DATE_MESSAGE_CONSTRAINTS = "Dates should be in the format YYYY-MM-DD.";
    public static final String TIME_MESSAGE_CONSTRAINTS = "Times should be in the format HH:MM (24-hour-format).";
    public static final String MESSAGE_CONSTRAINTS = "Appointments should have both a date and a time that "
            + "adhere to the following constraints:\n"
            + "1. " + DATE_MESSAGE_CONSTRAINTS + "\n"
            + "2. " + TIME_MESSAGE_CONSTRAINTS + ".\n"
            + "The date and time should represent a valid future appointment time.";


    public final Date date;
    public final Time time;

    /**
     * Constructs an {@code AppointmentEvent} with String.
     *
     * @param date A valid date String.
     * @param time A valid time String.
     */
    public AppointmentEvent(Date date, Time time) {
        requireNonNull(date);
        requireNonNull(time);
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "AppointmentEvent Date: "
                + date + " "
                + time;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentEvent)) {
            return false;
        }

        AppointmentEvent otherAppointmentEvent = (AppointmentEvent) other;
        return date.equals(otherAppointmentEvent.date) && time.equals(otherAppointmentEvent.time);
    }

    @Override
    public int hashCode() {
        return date.hashCode() + time.hashCode();
    }
}
