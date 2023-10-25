package seedu.cc.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.parser.CliSyntax.*;
import static seedu.cc.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.cc.commons.core.index.Index;
import seedu.cc.commons.util.ToStringBuilder;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.patient.Appointment;
import seedu.cc.model.patient.Patient;

/**
 * Edits the details of an existing person in the address book.
 */
public class AddApptCommand extends Command {

    public static final String COMMAND_WORD = "add-appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the patient identified "
            + "by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_APPT_DATE + "DATE "
            + PREFIX_APPT_TIME + "TIME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPT_DATE + "2023-10-01 "
            + PREFIX_APPT_TIME + "14:00";

    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "Successfully added appointment: %1$s";
    public static final String MESSAGE_INVALID_INPUT = "Invalid input. Please enter a valid patient index, date, or time.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the address book.";

    private final Index index;
    private final Appointment appointment;

    public AddApptCommand(Index index, Appointment appointment) {
        requireNonNull(index);
        requireNonNull(appointment);

        this.index = index;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

//        collect all appointments from model
//        model.addAppointmentToPatient(patientIndex, newAppointment);
//        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);


        Patient patientToAddAppt = lastShownList.get(index.getZeroBased());
        Patient patientAddedAppt = patientToAddAppt.addAppointment(this.appointment);

        if (!patientToAddAppt.isSamePerson(patientAddedAppt) && model.hasPatient(patientAddedAppt)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.setPatient(patientToAddAppt, patientAddedAppt);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_APPOINTMENT_SUCCESS, Messages.format(patientAddedAppt)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApptCommand)) {
            return false;
        }

        AddApptCommand otherEditCommand = (AddApptCommand) other;
        return index.equals(otherEditCommand.index)
                && appointment.equals(otherEditCommand.appointment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("appointment", appointment)
                .toString();
    }
}
