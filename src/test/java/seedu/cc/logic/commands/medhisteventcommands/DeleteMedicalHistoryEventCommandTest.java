package seedu.cc.logic.commands.medhisteventcommands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_MEDHISTEVENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.DeleteCommand;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;

class DeleteMedicalHistoryEventCommandTest {

    private Model model = new ModelManager(getTypicalClinicBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listMedicalHistoryEvents(patient);
        MedicalHistoryEvent eventToDelete = model.getFilteredMedicalHistoryEventList().get(INDEX_FIRST_MEDHISTEVENT
                .getZeroBased());
        DeleteMedicalHistoryEventCommand deleteCommand = new DeleteMedicalHistoryEventCommand(INDEX_FIRST_MEDHISTEVENT, INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(DeleteMedicalHistoryEventCommand.MESSAGE_DELETE_MEDICAL_HISTORY_SUCCESS,
                    eventToDelete);

        ModelManager expectedModel = new ModelManager(model.getClinicBook(), new UserPrefs());
        Patient expectedPatient = expectedModel.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        expectedModel.listMedicalHistoryEvents(expectedPatient);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeleteMedicalHistoryEventCommand deleteCommand = new DeleteMedicalHistoryEventCommand(INDEX_FIRST_MEDHISTEVENT, outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

   @Test
   public void execute_invalidEventIndexUnfilteredList_throwsCommandException() {
        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listMedicalHistoryEvents(patient);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMedicalHistoryEventList().size() + 1);
        DeleteMedicalHistoryEventCommand deleteCommand = new DeleteMedicalHistoryEventCommand(outOfBoundIndex, INDEX_FIRST_PATIENT);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEDICAL_HISTORY_EVENT_DISPLAYED_INDEX);
   }

    @Test
    public void equals() {
        DeleteMedicalHistoryEventCommand deleteFirstCommand = new DeleteMedicalHistoryEventCommand(INDEX_FIRST_PATIENT, INDEX_FIRST_MEDHISTEVENT);
        DeleteMedicalHistoryEventCommand deleteSecondCommand = new DeleteMedicalHistoryEventCommand(INDEX_SECOND_PATIENT, INDEX_FIRST_MEDHISTEVENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteMedicalHistoryEventCommand deleteFirstCommandCopy = new DeleteMedicalHistoryEventCommand(INDEX_FIRST_PATIENT, INDEX_FIRST_MEDHISTEVENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index eventIndex = Index.fromOneBased(1);
        Index patientIndex = Index.fromOneBased(1);
        DeleteMedicalHistoryEventCommand deleteCommand = new DeleteMedicalHistoryEventCommand(eventIndex, patientIndex);
        String expected = DeleteMedicalHistoryEventCommand.class.getCanonicalName() + "{eventIndex=" + eventIndex +
                ", patientIndex=" + patientIndex +"}";
        assertEquals(expected, deleteCommand.toString());
    }
}