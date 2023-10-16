package seedu.cc.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.cc.commons.exceptions.DataLoadingException;
import seedu.cc.model.ReadOnlyClinicBook;
import seedu.cc.model.NewReadOnlyUserPrefs;
import seedu.cc.model.NewUserPrefs;

/**
 * API of the Storage component
 */
public interface ClinicStorage extends ClinicBookStorage, NewUserPrefsStorage {

    @Override
    Optional<NewUserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(NewReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClinicBookFilePath();

    @Override
    Optional<ReadOnlyClinicBook> readClinicBook() throws DataLoadingException;

    @Override
    void saveClinicBook(ReadOnlyClinicBook addressBook) throws IOException;

}
