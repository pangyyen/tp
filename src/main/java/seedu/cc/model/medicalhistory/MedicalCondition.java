package seedu.cc.model.medicalhistory;

public class MedicalCondition {
    public final String value;


    public MedicalCondition(String medicalCondition) {
        this.value = medicalCondition;
    }

    public String getMedicalCondition() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
