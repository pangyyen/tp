package seedu.cc.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;

public class AppointmentsPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentsPanel.class);

    @javafx.fxml.FXML
    private ListView<MedicalHistoryEvent> medicalHistoryEventListView;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public AppointmentsPanel(ObservableList<MedicalHistoryEvent> medicalHistory) {
        super(FXML);
        medicalHistoryEventListView.setItems(medicalHistory);
        medicalHistoryEventListView.setCellFactory(listView -> new AppointmentsPanel
                .AppointmentsEventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class AppointmentsEventListViewCell extends ListCell<MedicalHistoryEvent> {
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
