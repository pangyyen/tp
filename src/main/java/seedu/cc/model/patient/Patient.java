package seedu.cc.model.patient;

import java.util.ArrayList;
import java.util.Set;

import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.PatientAppointmentList;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.medicalhistory.PatientMedicalHistory;
import seedu.cc.model.person.Address;
import seedu.cc.model.person.Email;
import seedu.cc.model.person.Name;
import seedu.cc.model.person.Person;
import seedu.cc.model.person.Phone;
import seedu.cc.model.tag.Tag;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {
    private final Nric nric;
    private PatientAppointmentList patientAppointmentList;
    private PatientMedicalHistory patientMedicalHistory;

    /**
     * Every field must be present and not null, except appointment and medical history.
     *
     */
    public Patient(Name name, Nric nric, Phone phone, Email email,
                   Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.nric = nric;
        this.patientAppointmentList = new PatientAppointmentList();
        this.patientMedicalHistory = new PatientMedicalHistory();
    }

    /**
     * Constructor for Patient with PatientMedicalHistory in Storage.
     *
     */
    public Patient(Name name, Nric nric, Phone phone, Email email, Address address,
                   PatientMedicalHistory patientMedicalHistory, Set<Tag> tags) {
        this(name, nric, phone, email, address, tags);
        this.patientMedicalHistory = patientMedicalHistory;
    }

    /**
     * Constructor for Patient with PatientAppointmentList in Storage.
     */
    public Patient(Name name, Nric nric, Phone phone, Email email, Address address,
                   PatientAppointmentList patientAppointmentList, Set<Tag> tags) {
        this(name, nric, phone, email, address, tags);
        this.patientAppointmentList = patientAppointmentList;
    }

    /**
     * Constructor for Patient with PatientAppointmentList and PatientMedicalHistory in Storage.
     */
    public Patient(Name name, Nric nric, Phone phone, Email email, Address address,
                   PatientAppointmentList patientAppointmentList, PatientMedicalHistory patientMedicalHistory,
                   Set<Tag> tags) {
        this(name, nric, phone, email, address, tags);
        this.patientAppointmentList = patientAppointmentList;
        this.patientMedicalHistory = patientMedicalHistory;
    }

    //getters and setters
    public Name getName() {
        return super.getName();
    }

    public Nric getNric() {
        return this.nric;
    }

    public Phone getPhone() {
        return super.getPhone();
    }

    public Email getEmail() {
        return super.getEmail();
    }

    public Address getAddress() {
        return super.getAddress();
    }

    public Set<Tag> getTags() {
        return super.getTags();
    }

    public PatientAppointmentList getPatientAppointmentList() {
        return this.patientAppointmentList;
    }

    public PatientMedicalHistory getPatientMedicalHistory() {
        return this.patientMedicalHistory;
    }

    /**
     * Returns true if both patients have the same nric.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        return super.isSamePerson(otherPatient) && this.nric.equals(otherPatient.getNric())
                && this.patientAppointmentList.equals(otherPatient.getAppointmentList())
                && this.patientMedicalHistory.equals(otherPatient.getMedicalHistory());
    }

    public boolean equals(Object other) {
        return super.equals(other);
    }

    public String toString() {
        return super.toString();
    }

    /**
     * ==================================
     * Methods for Medical History Events
     * ==================================
     */

    public PatientMedicalHistory getMedicalHistory() {
        return this.patientMedicalHistory;
    }

    public ArrayList<MedicalHistoryEvent> getClinicBookMedicalHistory() {
        return this.patientMedicalHistory.getMedicalHistoryEvents();
    }

    public void addMedicalHistoryEvent(MedicalHistoryEvent event) {
        this.patientMedicalHistory.addMedicalHistoryEvent(event);
    }

    public void setMedicalHistoryEvent(MedicalHistoryEvent eventToEdit, MedicalHistoryEvent editedEvent) {
        this.patientMedicalHistory.setMedicalHistoryEvent(eventToEdit, editedEvent);
    }

    public void deleteMedicalHistoryEvent(MedicalHistoryEvent eventToDelete) {
        this.patientMedicalHistory.deleteMedicalHistoryEvent(eventToDelete);
    }

    public boolean hasMedicalHistoryEvent(MedicalHistoryEvent event) {
        return this.patientMedicalHistory.hasMedicalHistoryEvent(event);
    }

    /**
     * ==================================
     * Methods for AppointmentEvent
     * ==================================
     */

    public PatientAppointmentList getAppointmentList() {
        return this.patientAppointmentList;
    }

    public ArrayList<AppointmentEvent> getClinicBookAppointmentList() {
        return this.patientAppointmentList.getAppointmentList();
    }

    public boolean hasAppointmentEvent(AppointmentEvent appointmentEvent) {
        return this.patientAppointmentList.hasAppointment(appointmentEvent);
    }

    public void addAppointmentEvent(AppointmentEvent appointmentEvent) {
        this.patientAppointmentList.addAppointment(appointmentEvent);
    }

    public void setAppointmentEvent(AppointmentEvent appointmentEventToEdit, AppointmentEvent editedAppointmentEvent) {
        this.patientAppointmentList.setAppointment(appointmentEventToEdit, editedAppointmentEvent);
    }

    public void deleteAppointmentEvent(AppointmentEvent appointmentEventToDelete) {
        this.patientAppointmentList.deleteAppointment(appointmentEventToDelete);
    }
}
