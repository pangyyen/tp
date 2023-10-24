package seedu.cc.logic.commands.medhisteventcommands;

import static java.util.Objects.requireNonNull;

import static seedu.cc.logic.parser.CliSyntax.PREFIX_PATIENT_INDEX;

import java.util.List;

import seedu.cc.commons.core.index.Index;
import seedu.cc.commons.util.ToStringBuilder;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.DeleteCommand;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.medicalhistory.expections.MedicalHistoryEventNotFoundException;
import seedu.cc.model.patient.Patient;

public class DeleteMedicalHistoryEventCommand extends Command {

    public static final String COMMAND_WORD = "delete-medical-history";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PATIENT_INDEX + "PATIENT INDEX ";

    public static final String MESSAGE_DELETE_MEDICAL_HISTORY_SUCCESS = "Deleted Medical History: %1$s";

    private final Index eventIndex;

    private final Index patientIndex;

    public DeleteMedicalHistoryEventCommand(Index eventIndex, Index patientIndex) {
        this.eventIndex = eventIndex;
        this.patientIndex = patientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastPatientShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastPatientShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient personToDeleteMedicalHistoryEvent = lastPatientShownList.get(patientIndex.getZeroBased());

        List<MedicalHistoryEvent> lastShownList = model.getFilteredMedicalHistoryEventList();

        if (eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        MedicalHistoryEvent medicalHistoryEventToDelete = lastShownList.get(eventIndex.getZeroBased());

        if (!personToDeleteMedicalHistoryEvent.hasMedicalHistoryEvent(medicalHistoryEventToDelete)) {
            throw new CommandException("This medical history event does not exist for this patient");
        }

        model.deleteMedicalHistoryEvent(personToDeleteMedicalHistoryEvent, medicalHistoryEventToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_MEDICAL_HISTORY_SUCCESS, medicalHistoryEventToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMedicalHistoryEventCommand)) {
            return false;
        }

        DeleteMedicalHistoryEventCommand otherDeleteCommand = (DeleteMedicalHistoryEventCommand) other;
        return eventIndex.equals(otherDeleteCommand.eventIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("eventIndex", eventIndex)
                .add("patientIndex", patientIndex)
                .toString();
    }
}
