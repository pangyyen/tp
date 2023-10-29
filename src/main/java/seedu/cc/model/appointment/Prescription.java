package seedu.cc.model.appointment;

/**
 * Represents a patient's prescription.
 */
public class Prescription {
    public final String value;

    public Prescription(String prescription) {
        this.value = prescription;
    }

    public String getPrescription() {
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
        if (!(other instanceof Prescription)) {
            return false;
        }

        Prescription otherPrescription = (Prescription) other;
        return value.equals(otherPrescription.value);
    }
}
