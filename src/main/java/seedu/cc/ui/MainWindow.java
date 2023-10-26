package seedu.cc.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.cc.commons.core.GuiSettings;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.commons.core.tabs.Tabs;
import seedu.cc.logic.Logic;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PatientListPanel patientListPanel;
    private MedicalHistoryPanel medicalHistoryPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    private int tab;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane medicalHistoryListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private Label tabInfoLabel;

    @FXML
    private TabPane mainTabPane;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.tab = logic.getCurrentTab();

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        //setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    //    private void setAccelerators() {
    //        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    //    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        logic.currentTabProperty().addListener((observable, oldValue, newValue) -> {
            changeTabs(newValue.intValue());
        });

        patientListPanel = new PatientListPanel(logic.getFilteredPatientList());
        personListPanelPlaceholder.getChildren().add(patientListPanel.getRoot());

        medicalHistoryPanel = new MedicalHistoryPanel(logic.getFilteredMedicalHistoryEventList());
        medicalHistoryListPanelPlaceholder.getChildren().add(medicalHistoryPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getClinicBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PatientListPanel getPatientListPanel() {
        return patientListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.cc.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Switches to the Patients tab.
     */
    @FXML
    public void showPatientsTab() {
        mainTabPane.getSelectionModel().select(0);
        tabInfoLabel.setText("Patients");
    }

    /**
     * Switches to the Medical History tab.
     */
    @FXML
    public void showMedicalHistoryTab() {
        mainTabPane.getSelectionModel().select(1);
        tabInfoLabel.setText("Medical History");
    }

    /**
     * Switches to the Appointments tab.
     */
    @FXML
    public void showAppointmentsTab() {
        mainTabPane.getSelectionModel().select(2);
        tabInfoLabel.setText("Appointments");
    }

    /**
     * Changes the tab to the specified tab index.
     * @param tabIndex the index of the tab to change to
     */
    public void changeTabs(int tabIndex) {
        mainTabPane.getSelectionModel().select(tabIndex);
        switch (tabIndex) {
        case 0:
            tabInfoLabel.setText(Tabs.PATIENTS.toString());
            break;
        case 1:
            tabInfoLabel.setText(Tabs.MEDICAL_HISTORY.toString());
            break;
        case 2:
            tabInfoLabel.setText(Tabs.APPOINTMENTS.toString());
            break;
        default:
            tabInfoLabel.setText("Unknown Tab");
            break;
        }
    }

}
