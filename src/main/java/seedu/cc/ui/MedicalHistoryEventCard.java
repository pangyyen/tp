package seedu.cc.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;

public class MedicalHistoryEventCard extends UiPart<Region>{
    private static final String FXML = "MedicalHistoryEventCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final MedicalHistoryEvent medicalHistoryEvent;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private Label medicalCondition;
    @FXML
    private Label treatment;


    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public MedicalHistoryEventCard(MedicalHistoryEvent medicalHistoryEvent, int displayedIndex) {
        super(FXML);
        this.medicalHistoryEvent = medicalHistoryEvent;
        date.setText(medicalHistoryEvent.getDate().toString());
        medicalCondition.setText(medicalHistoryEvent.getMedicalCondition().toString());
        treatment.setText(medicalHistoryEvent.getTreatment().toString());
    }
}
