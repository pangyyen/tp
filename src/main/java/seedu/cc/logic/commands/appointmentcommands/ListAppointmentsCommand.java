package seedu.cc.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.patient.Patient;

/**
 * Lists all appointments of a patient.
 */
public class ListAppointmentsCommand extends Command {
    public static final String COMMAND_WORD = "list-appointments";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";

    private final Index patientIndex;

    public ListAppointmentsCommand(Index patientIndex) {
        this.patientIndex = patientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patient = lastShownList.get(patientIndex.getZeroBased());
        model.listAppointmentsEventForPatient(patient);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
