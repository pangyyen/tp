package seedu.cc.model.medicalhistory;

/**
 * Represents a patient's treatment.
 */
public class Treatment {
    public final String value;

    public Treatment(String treatment) {
        this.value = treatment;
    }

    public String getTreatment() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
