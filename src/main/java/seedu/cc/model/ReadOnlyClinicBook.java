package seedu.cc.model;

import javafx.collections.ObservableList;
import seedu.cc.model.patient.Patient;
import seedu.cc.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyClinicBook {

    /**
     * Returns an unmodifiable view of the patient list.
     * This list will not contain any duplicate patients.
     */
    ObservableList<Patient> getPatientList();

}
