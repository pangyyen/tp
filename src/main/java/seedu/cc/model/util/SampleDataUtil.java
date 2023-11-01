package seedu.cc.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.cc.model.ClinicBook;
import seedu.cc.model.ReadOnlyClinicBook;
import seedu.cc.model.medicalhistory.MedicalCondition;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.medicalhistory.PatientMedicalHistory;
import seedu.cc.model.medicalhistory.Treatment;
import seedu.cc.model.patient.Nric;
import seedu.cc.model.patient.Patient;
import seedu.cc.model.person.Age;
import seedu.cc.model.person.Email;
import seedu.cc.model.person.Name;
import seedu.cc.model.person.Phone;
import seedu.cc.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static PatientMedicalHistory getSampleMedicalHistory() {
        PatientMedicalHistory samplePatientMedicalHistory = new PatientMedicalHistory();
        samplePatientMedicalHistory
                .addMedicalHistoryEvent(new MedicalHistoryEvent(new MedicalCondition("Insomia"),
                        new Treatment("Medication"), new Date("2023-10-23")));
        return samplePatientMedicalHistory;
    }

    public static Patient[] getSamplePatients() {
        return new Patient[]{
            new Patient(new Name("Alex Yeoh"), new Nric("S5323891B"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Age("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Patient(new Name("Bernice Yu"), new Nric("S5323891B"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Age("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Patient(new Name("Charlotte Oliveiro"), new Nric("S5323891B"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Age("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Patient(new Name("David Li"), new Nric("S5323891B"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Age("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Patient(new Name("Irfan Ibrahim"), new Nric("S5323891B"), new Phone("92492021"),
                new Email("irfan@example.com"), new Age("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Patient(new Name("Roy Balakrishnan"), new Nric("S5323891B"), new Phone("92624417"),
                new Email("royb@example.com"), new Age("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyClinicBook getSampleClinicBook() {
        ClinicBook sampleAb = new ClinicBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
