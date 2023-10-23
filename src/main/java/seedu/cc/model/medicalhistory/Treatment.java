package seedu.cc.model.medicalhistory;

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
