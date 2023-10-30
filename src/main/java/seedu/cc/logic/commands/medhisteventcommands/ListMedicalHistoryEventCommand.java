package seedu.cc.logic.commands.medhisteventcommands;

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
 * Lists all medical history events of a patient.
 */
public class ListMedicalHistoryEventCommand extends Command {
    public static final String COMMAND_WORD = "list-medical-history";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all medical history events of a patient identified by the index number used in the displayed "
            + "patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Listed all medical history for: \n%1$s";

    private final Index patientIndex;

    public ListMedicalHistoryEventCommand(Index patientIndex) {
        this.patientIndex = patientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patient = lastShownList.get(patientIndex.getZeroBased());
        model.listMedicalHistoryEvents(patient);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                Messages.format(patient)));
    }

}
