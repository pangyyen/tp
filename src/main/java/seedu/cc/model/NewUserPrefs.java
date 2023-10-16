package seedu.cc.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.cc.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class NewUserPrefs implements NewReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path clinicBookFilePath = Paths.get("data" , "clinicbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public NewUserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public NewUserPrefs(NewReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(NewReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setClinicBookFilePath(newUserPrefs.getClinicBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getClinicBookFilePath() {
        return clinicBookFilePath;
    }

    public void setClinicBookFilePath(Path clinicBookFilePath) {
        requireNonNull(clinicBookFilePath);
        this.clinicBookFilePath = clinicBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        NewUserPrefs otherUserPrefs = (NewUserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && clinicBookFilePath.equals(otherUserPrefs.clinicBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, clinicBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + clinicBookFilePath);
        return sb.toString();
    }

}
