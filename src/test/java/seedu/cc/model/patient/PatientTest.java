package seedu.cc.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.cc.model.appointment.PatientAppointmentList;
import seedu.cc.model.medicalhistory.PatientMedicalHistory;
import seedu.cc.model.person.Address;
import seedu.cc.model.person.Email;
import seedu.cc.model.person.Name;
import seedu.cc.model.person.Phone;
import seedu.cc.model.tag.Tag;

public class PatientTest {
    private Patient patient;
    private Patient patientEmptyAppointment;
    private Patient patientEmptyMedicalHistory;

    @BeforeEach
    public void setUp() {
        Name name = new Name("John Doe");
        Nric nric = new Nric("M1234567A");
        Phone phone = new Phone("98765432");
        Email email = new Email("johndoe@example.com");
        Address address = new Address("123, Jurong West Ave 6, #08-111");
        Set<Tag> tags = new HashSet<>();

        patient = new Patient(name, nric, phone, email, address, tags);
        patientEmptyAppointment = new Patient(name, nric, phone, email, address, new PatientAppointmentList(), tags);
        patientEmptyMedicalHistory = new Patient(name, nric, phone, email, address, new PatientMedicalHistory(), tags);
    }

    @Test
    public void isSamePatient_sameNric_returnsTrue() {
        Patient anotherPatient = new Patient(patient.getName(), patient.getNric(), patient.getPhone(),
                patient.getEmail(), patient.getAddress(), patient.getTags());
        assertTrue(patient.isSamePatient(anotherPatient));
    }

    @Test
    public void isSamePatient_differentNric_returnsFalse() {
        Nric diffNric = new Nric("M7654321B");
        Patient anotherPatient = new Patient(patient.getName(), diffNric, patient.getPhone(),
                patient.getEmail(), patient.getAddress(), patient.getTags());
        assertFalse(patient.isSamePatient(anotherPatient));
    }

    @Test
    public void getMedicalHistory_returnsCorrectMedicalHistory() {
        assertEquals(patient.getPatientMedicalHistory(), patientEmptyMedicalHistory.getPatientMedicalHistory());
    }

    @Test
    public void getAppointmentList_returnsCorrectAppointmentList() {
        assertEquals(patient.getPatientAppointmentList(), patientEmptyAppointment.getPatientAppointmentList());
    }
}
