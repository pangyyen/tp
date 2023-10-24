package seedu.cc.model.medicalhistory.expections;

public class MedicalHistoryEventNotFoundException extends RuntimeException {
    public MedicalHistoryEventNotFoundException() {
        super("Medical history not found");
    }
}

