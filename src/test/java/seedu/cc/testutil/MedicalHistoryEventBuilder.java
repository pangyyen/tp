package seedu.cc.testutil;

import seedu.cc.model.medicalhistory.Date;
import seedu.cc.model.medicalhistory.MedicalCondition;
import seedu.cc.model.medicalhistory.MedicalHistory;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.medicalhistory.Treatment;

public class MedicalHistoryEventBuilder {

    private static final String DEFAULT_DATE = "2023-10-23";
    private static final String DEFAULT_MEDICAL_CONDITION = "Heart Attack";
    private static final String DEFAULT_TREATMENT = "Heart Surgery";

    private Date date;
    private MedicalCondition medicalCondition;
    private Treatment treatment;


    public MedicalHistoryEventBuilder() {
        date = new Date(DEFAULT_DATE);
        medicalCondition = new MedicalCondition(DEFAULT_MEDICAL_CONDITION);
        treatment = new Treatment(DEFAULT_TREATMENT);
    }

    public MedicalHistoryEventBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    public MedicalHistoryEventBuilder withMedicalCondition(String medicalCondition) {
        this.medicalCondition = new MedicalCondition(medicalCondition);
        return this;
    }

    public MedicalHistoryEventBuilder withTreatment(String treatment) {
        this.treatment = new Treatment(treatment);
        return this;
    }


    public MedicalHistoryEvent build() {
        return new MedicalHistoryEvent(medicalCondition, treatment, date);
    }

    public MedicalHistory buildMedicalHistory() {
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.addMedicalHistoryEvent(new MedicalHistoryEvent(medicalCondition, treatment, date));
        return medicalHistory;
    }
}
