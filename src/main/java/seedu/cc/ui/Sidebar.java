package seedu.cc.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Sidebar {

    @FXML
    private Button patientsButton;

    @FXML
    private Button medicalHistoryButton;

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button helpButton;

    @FXML
    private ImageView patientsIcon;

    @FXML
    private ImageView historyIcon;

    @FXML
    private ImageView appointmentsIcon;

    @FXML
    private ImageView exitIcon;

    @FXML
    private ImageView helpIcon;

    @FXML
    public void initialize() {
        // This method is called after all @FXML annotated members have been injected.
        // You can add any initialization code here.
    }

    @FXML
    private void handleExit() {
        // Handle the exit action (as per your onAction="#handleExit" in the FXML).
        // For instance, close the application, or navigate away.
    }

    @FXML
    private void handleHelp() {
        // Handle the help action (as per your onAction="#handleHelp" in the FXML).
        // For instance, open a help dialog or redirect to a help page.
    }

    // You can add any other methods or event handlers specific to the sidebar here.
}
