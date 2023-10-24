package seedu.cc.model.medicalhistory;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cc.model.medicalhistory.expections.DuplicateMedicalHistoryEventException;
import seedu.cc.model.medicalhistory.expections.MedicalHistoryEventNotFoundException;
import seedu.cc.model.patient.Patient;
import seedu.cc.model.patient.exceptions.PatientNotFoundException;

/**
 * A list of medical history events that enforces uniqueness between its elements and does not allow nulls.
 * A medical history event is considered unique by comparing using {@code MedicalHistoryEvent#equals(Object)}.
 */
public class MedicalHistoryEventList implements Iterable<MedicalHistoryEvent> {

    private final ObservableList<MedicalHistoryEvent> internalList = FXCollections.observableArrayList();
    private final ObservableList<MedicalHistoryEvent> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private Patient currentPatient = null;

    /**
     * Returns true if the list contains an equivalent medical history event as the given argument.
     */
    public boolean contains(MedicalHistoryEvent toCheck) {
        return internalList.contains(toCheck);
    }

    /**
     * Adds a medical history event to the list.
     * The event must not already exist in the list.
     */
    public void add(MedicalHistoryEvent toAdd, Patient patient) {
        if (contains(toAdd)) {
            throw new DuplicateMedicalHistoryEventException();
        }

        if (!patient.equals(currentPatient)) {
            return;
        }

        internalList.add(toAdd);
    }

    /**
     * Replaces the given medical history event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the list.
     * The medical history event must not be the same as another existing event in the list.
     */
    public void setMedicalHistoryEvent(MedicalHistoryEvent target, MedicalHistoryEvent editedEvent) {
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MedicalHistoryEventNotFoundException();
        }

        if (!target.equals(editedEvent) && contains(editedEvent)) {
            throw new DuplicateMedicalHistoryEventException();
        }

        internalList.set(index, editedEvent);
    }

    /**
     * Removes the equivalent medical history event from the list.
     * The event must exist in the list.
     */
    public void remove(MedicalHistoryEvent toRemove) {
        if (!internalList.remove(toRemove)) {
            throw new MedicalHistoryEventNotFoundException();
        }
    }


    public void listMedicalHistoryEvents(Patient patient) {
        requireNonNull(patient);
        internalList.setAll(patient.getMedicalHistoryEvents());
    }

    /**
     * Returns a list of all medical history events in this list.
     */
    public List<MedicalHistoryEvent> getAllMedicalHistoryEvents() {
        return new ArrayList<>(internalList);
    }

    public ObservableList<MedicalHistoryEvent> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<MedicalHistoryEvent> iterator() {
        return internalList.iterator();
    }

}
