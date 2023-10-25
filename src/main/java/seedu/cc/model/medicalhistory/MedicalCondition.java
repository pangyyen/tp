package seedu.cc.model.medicalhistory;

/**
 * Represents a patient's medical condition.
 */
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedicalCondition)) {
            return false;
        }

        MedicalCondition otherMedicalCondition = (MedicalCondition) other;
        return value.equals(otherMedicalCondition.value);
    }
}
