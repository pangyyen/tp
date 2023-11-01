package seedu.cc.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_APPT_DATE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_APPT_TIME;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PATIENT_INDEX;

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
import seedu.cc.model.patient.Patient;
import seedu.cc.model.util.Date;
import seedu.cc.model.util.Time;

/**
 * Edits the details of an existing appointment event in the address book.
 */
public class EditAppointmentEventCommand extends Command {

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

    /**
     * Edit the appointment event at {@code eventIndex} from the patient at {@code patientIndex}.
     * @param patientIndex               of the appointment event in the filtered appointment event list to edit
     * @param editAppointmentEventDescriptor details to edit the appointment event with
     */
    public EditAppointmentEventCommand(Index eventIndex, Index patientIndex,
                                       EditAppointmentEventDescriptor editAppointmentEventDescriptor) {
        this.eventIndex = eventIndex;
        this.patientIndex = patientIndex;
        this.editAppointmentEventDescriptor = editAppointmentEventDescriptor;
    }

    /**
     * Creates and returns a {@code AppointmentEvent} with the details of {@code eventToEdit}
     * edited with {@code editAppointmentEventDescriptor}.
     */
    private static AppointmentEvent createEditedAppointmentEvent(
            AppointmentEvent eventToEdit, EditAppointmentEventDescriptor editAppointmentEventDescriptor) {

        Date updatedDate = editAppointmentEventDescriptor.getDate()
                .orElse(eventToEdit.getDate());

        Time updatedTime = editAppointmentEventDescriptor.getTime()
                .orElse(eventToEdit.getTime());

        return new AppointmentEvent(updatedDate, updatedTime);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> lastShownPatientList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownPatientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEditAppointmentEvent = lastShownPatientList.get(patientIndex.getZeroBased());

        // Check if the index is valid
        List<AppointmentEvent> lastShownList = model.getFilteredAppointmentList();

        if (eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_EVENT_DISPLAYED_INDEX);
        }
        // Retrieve the appointment event from the model based on the index
        // TODO: fix the violation of Talk to Stranger principle
        AppointmentEvent eventToEdit = model.getFilteredAppointmentList().get(eventIndex.getZeroBased());

        // Create a new appointment event with the updated details
        AppointmentEvent editedEvent = createEditedAppointmentEvent(eventToEdit, editAppointmentEventDescriptor);

        if (!patientToEditAppointmentEvent.hasAppointmentEvent(eventToEdit)) {
            throw new CommandException("This appointment event does not exist for this patient");
        }

        // Update the model with the edited event
        model.setAppointmentEventForPatient(patientToEditAppointmentEvent, eventToEdit, editedEvent);

        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS,
                Messages.format(editedEvent, patientToEditAppointmentEvent)));

    }
    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentEventDescriptor {
        private Date date;
        private Time time;

        public EditAppointmentEventDescriptor() {
        }

        /**
         * Copy constructor to create a new descriptor by copying the fields from another descriptor.
         */
        public EditAppointmentEventDescriptor(EditAppointmentEventDescriptor toCopy) {
            setDate(toCopy.date);
            setTime(toCopy.time);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, time);
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Time> getTime() {
            return Optional.ofNullable(time);
        }

        public void setTime(Time time) {
            this.time = time;
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
            return Objects.equals(date, otherDescriptor.date)
                    && Objects.equals(time, otherDescriptor.time);
        }

        @Override
        public int hashCode() {
            return Objects.hash(date, time);
        }
    }
}
