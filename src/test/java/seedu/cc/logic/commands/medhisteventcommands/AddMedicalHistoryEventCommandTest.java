package seedu.cc.logic.commands.medhisteventcommands;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.GuiSettings;
import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.medhisteventcommands.AddMedicalHistoryEventCommand;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.ReadOnlyClinicBook;
import seedu.cc.model.ReadOnlyUserPrefs;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;
import seedu.cc.testutil.MedicalHistoryEventBuilder;
import seedu.cc.testutil.PatientBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cc.testutil.Assert.assertThrows;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

public class AddMedicalHistoryEventCommandTest {

    private Model model = new ModelManager(getTypicalClinicBook(), new UserPrefs());

    @Test
    public void constructor_nullEventToAdd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMedicalHistoryEventCommand(null, Index.fromOneBased(1)));
    }

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        MedicalHistoryEvent validEvent = new MedicalHistoryEventBuilder().build();
        assertThrows(NullPointerException.class, () -> new AddMedicalHistoryEventCommand(validEvent, null));
    }

    @Test
    public void execute_medicalHistoryEventAcceptedByModel_addSuccessful() throws Exception {
        MedicalHistoryEvent validEvent = new MedicalHistoryEventBuilder().build();

        CommandResult commandResult = new AddMedicalHistoryEventCommand(validEvent, INDEX_FIRST_PATIENT).execute(model);

        assertEquals(String.format(AddMedicalHistoryEventCommand.MESSAGE_SUCCESS, validEvent),
                commandResult.getFeedbackToUser());

    }

}


