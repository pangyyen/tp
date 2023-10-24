package seedu.cc.model.medicalhistory;

import java.util.ArrayList;

import seedu.cc.model.person.Person;

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

    public void deleteMedicalHistoryEvent(MedicalHistoryEvent eventToDelete) {
        int index = medicalHistoryEvents.indexOf(eventToDelete);
        if (index >= 0 && index < medicalHistoryEvents.size()) {
            medicalHistoryEvents.remove(index);
        }
    }

    public boolean hasMedicalHistoryEvent(MedicalHistoryEvent event) {
        return medicalHistoryEvents.contains(event);
    }

    public void removeMedicalHistoryEvent(int index) {
        if (index >= 0 && index < medicalHistoryEvents.size()) {
            medicalHistoryEvents.remove(index);
        }
    }


}
