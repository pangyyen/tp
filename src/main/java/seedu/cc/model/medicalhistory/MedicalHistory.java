package seedu.cc.model.medicalhistory;

import java.util.ArrayList;

public class MedicalHistory {

    private final ArrayList<MedicalHistoryEvent> medicalHistoryEvents;

    public MedicalHistory() {
        this.medicalHistoryEvents = new ArrayList<>();
    }

    public void addMedicalHistoryEvent(MedicalHistoryEvent event) {
        medicalHistoryEvents.add(event);

    }

    public ArrayList<MedicalHistoryEvent> getMedicalHistoryEvents() {
        return new ArrayList<>(medicalHistoryEvents);
    }

    public void setMedicalHistoryEvent(MedicalHistoryEvent eventToEdit, MedicalHistoryEvent editedEvent) {
        int index = medicalHistoryEvents.indexOf(eventToEdit);
        if (index >= 0 && index < medicalHistoryEvents.size()) {
            medicalHistoryEvents.set(index, editedEvent);
        }
    }

    public void removeMedicalHistoryEvent(int index) {
        if (index >= 0 && index < medicalHistoryEvents.size()) {
            medicalHistoryEvents.remove(index);
        }
    }

    // You can add other methods as needed for your specific application
}
