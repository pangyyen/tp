package seedu.cc.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;

public class MedicalHistoryPanel extends UiPart<Region>{
    private static final String FXML = "MedicalHistoryPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PatientListPanel.class);

    @javafx.fxml.FXML
    private ListView<MedicalHistoryEvent> medicalHistoryEventListView;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public MedicalHistoryPanel(ObservableList<MedicalHistoryEvent> medicalHistory) {
        super(FXML);
        medicalHistoryEventListView.setItems(medicalHistory);
        medicalHistoryEventListView.setCellFactory(listView -> new MedicalHistoryPanel.MedicalHistoryEventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class MedicalHistoryEventListViewCell extends ListCell<MedicalHistoryEvent> {
        @Override
        protected void updateItem(MedicalHistoryEvent person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MedicalHistoryEventCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
