package seedu.cc.logic.commands.medhisteventcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;

public class ListMedicalHistoryEventCommand extends Command {
    public static final String COMMAND_WORD = "list-medical-history";

    public static final String MESSAGE_SUCCESS = "Listed all medical history";

    private final Index patientIndex;

    public ListMedicalHistoryEventCommand(Index patientIndex) {
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
        model.listMedicalHistoryEvents(patient);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
