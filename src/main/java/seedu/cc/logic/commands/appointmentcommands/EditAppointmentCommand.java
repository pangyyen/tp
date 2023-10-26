package seedu.cc.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.parser.CliSyntax.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.cc.commons.core.index.Index;
import seedu.cc.commons.util.CollectionUtil;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.medicalhistory.Date;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;

/**
 * Edits the details of a medical history event identified by the index number used in the displayed
 * medical history event list.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "edit-appt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment "
            + "identified by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1"
            + PREFIX_PATIENT_INDEX + "PATIENT INDEX "
            + PREFIX_APPT_DATE + "DATE"
            + PREFIX_APPT_TIME + "TIME ";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Appointment event: %1$s";

    private final Index patientIndex;
    private final Index eventIndex;
    private final EditAppointmentEventDescriptor editAppointmentEventDescriptor;

    public EditAppointmentCommand(Index eventIndex, Index patientIndex,
                                  EditAppointmentEventDescriptor editAppointmentEventDescriptor) {
        this.eventIndex = eventIndex;
        this.patientIndex = patientIndex;
        this.editAppointmentEventDescriptor = editAppointmentEventDescriptor;
    }

    private static AppointmentEvent createEditedAppointmentEvent(
            AppointmentEvent eventToEdit, EditAppointmentEventDescriptor editAppointmentEventDescriptor) {

        LocalDate updatedLocalDate = editAppointmentEventDescriptor.getLocalDate()
                .orElse(eventToEdit.getLocalDate());

        LocalTime updatedLocalTime = editAppointmentEventDescriptor.getLocalTime()
                .orElse(eventToEdit.getLocalTime());

        return new AppointmentEvent(updatedLocalDate, updatedLocalTime);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> lastShownPatientList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownPatientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEditAppointmentEvent = lastShownPatientList.get(patientIndex.getZeroBased());

        // Check if the index is valid
        List<AppointmentEvent> lastShownList = model.getFilteredAppointmentList();

        if (eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_EVENT_DISPLAYED_INDEX);
        }
        // Retrieve the medical history event from the model based on the index
        // TODO: fix the violation of Talk to Stranger principle
        AppointmentEvent eventToEdit = model.getFilteredAppointmentList().get(eventIndex.getZeroBased());

        // Create a new medical history event with the updated details
        AppointmentEvent editedEvent = createEditedAppointmentEvent(eventToEdit, editAppointmentEventDescriptor);

        if (!patientToEditAppointmentEvent.hasAppointmentEvent(eventToEdit)) {
            throw new CommandException("This medical history event does not exist for this patient");
        }

        // Update the model with the edited event
        model.setAppointmentEventForPatient(patientToEditAppointmentEvent, eventToEdit, editedEvent);

        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));

    }
    /**
     * Stores the details to edit the medical history with. Each non-empty field value will replace the
     * corresponding field value of the medical history.
     */
    public static class EditAppointmentEventDescriptor {
        private LocalDate localDate;
        private LocalTime localTime;

        public EditAppointmentEventDescriptor() {
        }

        /**
         * Copy constructor to create a new descriptor by copying the fields from another descriptor.
         */
        public EditAppointmentEventDescriptor(EditAppointmentEventDescriptor toCopy) {
            setLocalDate(toCopy.localDate);
            setLocalTime(toCopy.localTime);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(localDate, localTime);
        }

        public Optional<LocalDate> getLocalDate() {
            return Optional.ofNullable(localDate);
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public Optional<LocalTime> getLocalTime() {
            return Optional.ofNullable(localTime);
        }

        public void setLocalTime(LocalTime localTime) {
            this.localTime = localTime;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentEventDescriptor)) {
                return false;
            }

            EditAppointmentEventDescriptor otherDescriptor = (EditAppointmentEventDescriptor) other;
            return Objects.equals(localDate, otherDescriptor.localDate)
                    && Objects.equals(localTime, otherDescriptor.localTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(localDate, localTime);
        }
    }
}
