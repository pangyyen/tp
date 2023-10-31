package seedu.cc.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.Prescription;

/**
 * Jackson-friendly version of {@link AppointmentEvent}.
 * Jackson-friendly version of {@link AppointmentEvent}.
 */
public class JsonAdaptedAppointmentEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment event's %s field is missing!";

    private final LocalDate localDate;
    private final LocalTime localTime;
    private final List<JsonAdaptedPrescription> prescriptions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAppointmentEvent} with the given medical history event details.
     */
    @JsonCreator
    public JsonAdaptedAppointmentEvent(@JsonProperty("localDate") LocalDate localDate,
                                       @JsonProperty("localTime") LocalTime localTime,
                                       @JsonProperty("prescription") List<JsonAdaptedPrescription> prescriptions) {
        this.localDate = localDate;
        this.localTime = localTime;
        if (prescriptions != null) {
            this.prescriptions.addAll(prescriptions);
        }
    }

    /**
     * Converts a given {@code AppointmentEvent} into this class for Jackson use.
     */
    public JsonAdaptedAppointmentEvent(AppointmentEvent source) {
        localDate = source.getLocalDate();
        localTime = source.getLocalTime();
        this.prescriptions.addAll(source.getPrescriptions().stream()
                .map(JsonAdaptedPrescription::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted appointment event object into the model's {@code AppointmentEvent}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medical history event.
     */
    public AppointmentEvent toModelType() throws IllegalValueException {

        if (localDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Local Date"));
        }

        if (localTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Local Time"));
        }

        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions");
            return new AppointmentEvent(localDate, localTime);
        }

        final List<Prescription> patientPrescriptions = new ArrayList<>();
        for (JsonAdaptedPrescription prescription : prescriptions) {
            patientPrescriptions.add(prescription.toModelType());
        }
        final Set<Prescription> prescriptionSet = new HashSet<>(patientPrescriptions);
        return new AppointmentEvent(localDate, localTime, prescriptionSet);
    }
}
