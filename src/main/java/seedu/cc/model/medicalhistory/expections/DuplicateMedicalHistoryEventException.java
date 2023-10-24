package seedu.cc.model.medicalhistory.expections;

public class DuplicateMedicalHistoryEventException extends RuntimeException {
    public DuplicateMedicalHistoryEventException() {
        super("Operation would result in duplicate medical history");
    }
}

