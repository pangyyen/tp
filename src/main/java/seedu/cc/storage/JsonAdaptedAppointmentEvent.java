package seedu.cc.storage;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.util.Date;
import seedu.cc.model.util.Time;

/**
 * Jackson-friendly version of {@link AppointmentEvent}.
 * Jackson-friendly version of {@link AppointmentEvent}.
 */
public class JsonAdaptedAppointmentEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment event's %s field is missing!";

    private final Date date;
    private final Time time;

    /**
     * Constructs a {@code JsonAdaptedAppointmentEvent} with the given medical history event details.
     */
    @JsonCreator
    public JsonAdaptedAppointmentEvent(@JsonProperty("date") Date date,
                                           @JsonProperty("localTime") Time time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Converts a given {@code AppointmentEvent} into this class for Jackson use.
     */
    public JsonAdaptedAppointmentEvent(AppointmentEvent source) {
        date = source.getDate();
        time = source.getTime();
    }

    /**
     * Converts this Jackson-friendly adapted appointment event object into the model's {@code AppointmentEvent}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medical history event.
     */
    public AppointmentEvent toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Local Date"));
        }

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Local Time"));
        }

        return new AppointmentEvent(date, time);
    }
}
