package seedu.cc.logic.commands.medhisteventcommands;

import static seedu.cc.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_MEDHISTEVENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.medhisteventcommands.EditMedicalHistoryEventCommand.EditMedicalHistoryEventDescriptor;
import seedu.cc.model.ClinicBook;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;
import seedu.cc.testutil.EditMedicalHistoryEventDescriptorBuilder;
import seedu.cc.testutil.MedicalHistoryEventBuilder;
import seedu.cc.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditPatientMedicalHistoryEventCommandTest {

    private final Model model = new ModelManager(getTypicalClinicBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        MedicalHistoryEvent editedMedicalHistoryEvent = new MedicalHistoryEventBuilder().build();
        EditMedicalHistoryEventDescriptor descriptor =
                new EditMedicalHistoryEventDescriptorBuilder(editedMedicalHistoryEvent).build();
        EditMedicalHistoryEventCommand editCommand = new EditMedicalHistoryEventCommand(INDEX_FIRST_MEDHISTEVENT,
                INDEX_FIRST_PATIENT, descriptor);

        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listMedicalHistoryEvents(patient);

        String expectedMessage = String.format(EditMedicalHistoryEventCommand.MESSAGE_EDIT_EVENT_SUCCESS,
                editedMedicalHistoryEvent);

        Model expectedModel = new ModelManager(new ClinicBook(model.getClinicBook()), new UserPrefs());
        expectedModel.listMedicalHistoryEvents(patient);
        expectedModel.setMedicalHistoryEvent(patient, model.getFilteredMedicalHistoryEventList().get(0),
                editedMedicalHistoryEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {

        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listMedicalHistoryEvents(patient);


        MedicalHistoryEventBuilder eventInList = new MedicalHistoryEventBuilder();
        MedicalHistoryEvent editedEvent = eventInList.withMedicalCondition(VALID_MEDICAL_CONDITION)
                .withDate(VALID_DATE).build();

        PatientBuilder patientInList = new PatientBuilder(patient);
        Patient editedPatient = patientInList.withMedicalHistory(new MedicalHistoryEventBuilder().buildMedicalHistory())
                .build();

        Index indexLastMedicalHistoryEvent = Index.fromOneBased(model.getFilteredMedicalHistoryEventList().size());
        MedicalHistoryEvent lastMedicalHistoryEvent = model.getFilteredMedicalHistoryEventList()
                .get(indexLastMedicalHistoryEvent.getZeroBased());

        EditMedicalHistoryEventDescriptor descriptor = new EditMedicalHistoryEventDescriptorBuilder()
                .withMedicalCondition(VALID_MEDICAL_CONDITION).withDate(VALID_DATE).build();
        EditMedicalHistoryEventCommand editCommand = new EditMedicalHistoryEventCommand(indexLastMedicalHistoryEvent,
                INDEX_FIRST_PATIENT, descriptor);

        String expectedMessage = String.format(EditMedicalHistoryEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new ClinicBook(model.getClinicBook()), new UserPrefs());
        expectedModel.listMedicalHistoryEvents(editedPatient);
        expectedModel.setMedicalHistoryEvent(editedPatient, lastMedicalHistoryEvent, editedEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMedicalHistoryEventIndexUnfilteredList_failure() {
        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listMedicalHistoryEvents(patient);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMedicalHistoryEventList().size() + 1);


        EditMedicalHistoryEventDescriptor descriptor = new EditMedicalHistoryEventDescriptorBuilder()
                .withMedicalCondition(VALID_MEDICAL_CONDITION).build();
        EditMedicalHistoryEventCommand editCommand = new EditMedicalHistoryEventCommand(outOfBoundIndex,
                INDEX_FIRST_PATIENT, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MEDICAL_HISTORY_EVENT_DISPLAYED_INDEX);
    }
}

