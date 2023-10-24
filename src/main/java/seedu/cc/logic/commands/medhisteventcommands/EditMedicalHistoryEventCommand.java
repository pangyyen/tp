package seedu.cc.logic.commands.medhisteventcommands;

import static java.util.Objects.requireNonNull;

import static seedu.cc.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PATIENT_INDEX;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.cc.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
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
import seedu.cc.model.medicalhistory.MedicalCondition;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.medicalhistory.Treatment;
import seedu.cc.model.patient.Patient;

/**
 * Edits the details of a medical history event identified by the index number used in the displayed
 * medical history event list.
 */
public class EditMedicalHistoryEventCommand extends Command {

    public static final String COMMAND_WORD = "edit-medical-history";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the medical history event "
            + "identified by the index number used in the displayed medical history event list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1"
            + PREFIX_PATIENT_INDEX + "PATIENT INDEX "
            + PREFIX_DATE + "DATE "
            + PREFIX_MEDICAL_CONDITION + "MEDICAL CONDITION "
            + PREFIX_TREATMENT + "TREATMENT ";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Medical History Event: %1$s";

    private final Index patientIndex;

    private final Index eventIndex;
    private final EditMedicalHistoryEventDescriptor editMedHistEventDescriptor;

    /**
     * @param patientIndex                    of the medical history event in the filtered medical history event list to edit
     * @param editMedHistEventDescriptor details to edit the medical history event with
     */
    public EditMedicalHistoryEventCommand(Index eventIndex, Index patientIndex, EditMedicalHistoryEventDescriptor editMedHistEventDescriptor) {
        this.eventIndex = eventIndex;
        this.patientIndex = patientIndex;
        this.editMedHistEventDescriptor = editMedHistEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> lastShownPatientList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownPatientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEditMedicalHistoryEvent = lastShownPatientList.get(patientIndex.getZeroBased());

        // Check if the index is valid
        List<MedicalHistoryEvent> lastShownList = model.getFilteredMedicalHistoryEventList();

        if (eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEDICAL_HISTORY_EVENT_DISPLAYED_INDEX);
        }
        // Retrieve the medical history event from the model based on the index
        MedicalHistoryEvent eventToEdit = model.getFilteredMedicalHistoryEventList().get(eventIndex.getZeroBased());

        // Create a new medical history event with the updated details
        MedicalHistoryEvent editedEvent = createEditedMedicalHistoryEvent(eventToEdit, editMedHistEventDescriptor);

        if (!patientToEditMedicalHistoryEvent.hasMedicalHistoryEvent(eventToEdit)) {
            throw new CommandException("This medical history event does not exist for this patient");
        }

        // Update the model with the edited event
        model.setMedicalHistoryEvent(patientToEditMedicalHistoryEvent, eventToEdit, editedEvent);

        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));

    }

    /**
     * Creates and returns a new MedicalHistoryEvent with the details of the eventToEdit edited with editMedHistEventDescriptor.
     */
    private static MedicalHistoryEvent createEditedMedicalHistoryEvent(
            MedicalHistoryEvent eventToEdit, EditMedicalHistoryEventDescriptor editMedHistEventDescriptor) {

        MedicalCondition updatedMedicalCondition = editMedHistEventDescriptor.getMedicalCondition()
                .orElse(eventToEdit.getMedicalCondition());

        LocalDateTime updatedDate = editMedHistEventDescriptor.getDate()
                .orElse(eventToEdit.getDate());

        Treatment updatedTreatment = editMedHistEventDescriptor.getTreatment()
                .orElse(eventToEdit.getTreatment());

        return new MedicalHistoryEvent(updatedMedicalCondition, updatedTreatment, updatedDate);
    }

    public static class EditMedicalHistoryEventDescriptor {
        private LocalDateTime date;
        private Treatment treatment;
        private MedicalCondition medicalCondition;

        public EditMedicalHistoryEventDescriptor() {}

        /**
         * Copy constructor to create a new descriptor by copying the fields from another descriptor.
         */
        public EditMedicalHistoryEventDescriptor(EditMedicalHistoryEventDescriptor toCopy) {
            setDate(toCopy.date);
            setMedicalCondition(toCopy.medicalCondition);
            setTreatment(toCopy.treatment);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, medicalCondition, treatment);
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }

        public Optional<LocalDateTime> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTreatment(Treatment treatment) {
            this.treatment = treatment;
        }

        public Optional<Treatment> getTreatment() {
            return Optional.ofNullable(treatment);
        }

        public void setMedicalCondition(MedicalCondition medicalCondition) {
            this.medicalCondition = medicalCondition;
        }

        public Optional<MedicalCondition> getMedicalCondition() {
            return Optional.ofNullable(medicalCondition);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMedicalHistoryEventDescriptor)) {
                return false;
            }

            EditMedicalHistoryEventDescriptor otherDescriptor = (EditMedicalHistoryEventDescriptor) other;
            return Objects.equals(date, otherDescriptor.date)
                    && Objects.equals(treatment, otherDescriptor.treatment)
                    && Objects.equals(medicalCondition, otherDescriptor.medicalCondition);
        }

        @Override
        public int hashCode() {
            return Objects.hash(date, medicalCondition, treatment);
        }
    }
}
