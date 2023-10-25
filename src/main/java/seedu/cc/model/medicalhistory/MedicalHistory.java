package seedu.cc.model.medicalhistory;

import java.util.ArrayList;

/**
 * Represents a patient's medical history.
 */
public class MedicalHistory {

    private final ArrayList<MedicalHistoryEvent> medicalHistoryEvents;

    public MedicalHistory() {
        this.medicalHistoryEvents = new ArrayList<>();
    }

    /**
     * Creates a MedicalHistory using the MedicalHistoryEvents in the {@code toBeCopied}
     */
    public void addMedicalHistoryEvent(MedicalHistoryEvent event) {
        medicalHistoryEvents.add(event);

    }

    /**
     * Returns an ArrayList of MedicalHistoryEvents.
     * @return ArrayList of MedicalHistoryEvents.
     */
    public ArrayList<MedicalHistoryEvent> getMedicalHistoryEvents() {
        return medicalHistoryEvents;
    }

    /**
     * Sets the MedicalHistoryEvent at the specified index to the edited MedicalHistoryEvent.
     * @param eventToEdit
     * @param editedEvent
     */
    public void setMedicalHistoryEvent(MedicalHistoryEvent eventToEdit, MedicalHistoryEvent editedEvent) {
        int index = medicalHistoryEvents.indexOf(eventToEdit);
        if (index >= 0 && index < medicalHistoryEvents.size()) {
            medicalHistoryEvents.set(index, editedEvent);
        }
    }

    /**
     * Deletes the specified MedicalHistoryEvent from the MedicalHistory.
     * @param eventToDelete
     */
    public void deleteMedicalHistoryEvent(MedicalHistoryEvent eventToDelete) {
        int index = medicalHistoryEvents.indexOf(eventToDelete);
        if (index >= 0 && index < medicalHistoryEvents.size()) {
            medicalHistoryEvents.remove(index);
        }
    }

    /**
     * Returns true if the MedicalHistory contains the specified MedicalHistoryEvent.
     * @param event
     * @return
     */
    public boolean hasMedicalHistoryEvent(MedicalHistoryEvent event) {
        int s = 1;
        boolean as = medicalHistoryEvents.get(0).equals(event);
        return medicalHistoryEvents.contains(event);
    }


}
