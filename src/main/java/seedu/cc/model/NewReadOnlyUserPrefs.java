package seedu.cc.model;

import java.nio.file.Path;

import seedu.cc.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface NewReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getClinicBookFilePath();

}
