package seedu.cc.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;


/**
 * Represents a Patient's appointment in the clinic book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)} and {@link #isValidTime(String)}
 */
public class AppointmentEvent {
    public static final String DATE_MESSAGE_CONSTRAINTS = "Dates should be in the format YYYY-MM-DD.";
    public static final String TIME_MESSAGE_CONSTRAINTS = "Times should be in the format HH:MM (24-hour-format).";
    public static final String MESSAGE_CONSTRAINTS = "Appointments should have both a date and a time that "
            + "adhere to the following constraints:\n"
            + "1. " + DATE_MESSAGE_CONSTRAINTS + "\n"
            + "2. " + TIME_MESSAGE_CONSTRAINTS + ".\n"
            + "The date and time should represent a valid future appointment time.";


    private final LocalDate date;
    private final LocalTime time;
    private Set<Prescription> prescriptions = new HashSet<>();;
    private boolean isDone;

    /**
     * Constructs an {@code AppointmentEvent} with String.
     *
     * @param date A valid date String.
     * @param time A valid time String.
     */
    public AppointmentEvent(String date, String time) {
        requireNonNull(date);
        requireNonNull(time);
        checkArgument(isValidDate(date), DATE_MESSAGE_CONSTRAINTS);
        checkArgument(isValidTime(time), TIME_MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
        this.isDone = false;
        this.prescriptions = new HashSet<>();
    }

    /**
     * Constructs an {@code AppointmentEvent} with LocalDate and LocalTime.
     *
     * @param date A valid LocalDate.
     * @param time A valid LocalTime.
     */
    public AppointmentEvent(LocalDate date, LocalTime time) {
        requireNonNull(date);
        requireNonNull(time);
        checkArgument(isValidDate(date), DATE_MESSAGE_CONSTRAINTS);
        checkArgument(isValidTime(time), TIME_MESSAGE_CONSTRAINTS);
        this.date = date;
        this.time = time;
        this.isDone = false;
    }

    /**
     * Constructs an {@code AppointmentEvent} with LocalDate, LocalTime and Set of Prescriptions.
     *
     * @param date A valid LocalDate.
     * @param time A valid LocalTime.
     * @param prescriptions A valid Set of Prescriptions.
     */
    public AppointmentEvent(LocalDate date, LocalTime time, Set<Prescription> prescriptions) {
        requireNonNull(date);
        requireNonNull(time);
        checkArgument(isValidDate(date), DATE_MESSAGE_CONSTRAINTS);
        checkArgument(isValidTime(time), TIME_MESSAGE_CONSTRAINTS);
        this.date = date;
        this.time = time;
        this.isDone = false;
        this.prescriptions.addAll(prescriptions);
    }

    /**
     * Returns true if a given LocalDate is in valid format
     */
    public static boolean isValidDate(LocalDate testDate) {
        try {
            LocalDate.parse(testDate.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String testDate) {
        try {
            LocalDate.parse(testDate, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(LocalTime testTime) {
        try {
            LocalTime.parse(testTime.toString(), DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String testTime) {
        try {
            LocalTime.parse(testTime, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Set the appointment's prescriptions.
     * @param prescriptions
     *
     */
    public void Prescription(Set<Prescription> prescriptions) {
        this.prescriptions.addAll(prescriptions);
    }


    /**
     * Add the appointment's prescriptions.
     * @param prescriptions
     */
    public void addPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions.addAll(prescriptions);
    }
//    public void addPrescription(Prescription prescription) {
//        this.prescription = prescription;
//    }


    /**
     * Returns the prescriptions of the appointment.
     *
     */
    public Set<Prescription> getPrescriptions() {
        return this.prescriptions;
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public LocalTime getLocalTime() {
        return time;
    }

    @Override
    public String toString() {
        return "AppointmentEvent Date: "
                + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " "
                + time.format(DateTimeFormatter.ofPattern("HH:mm"));
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
