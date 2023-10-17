package seedu.cc.logic.commands;

import seedu.cc.model.NewModel;

/**
 * Terminates the program.
 */
public class ExitClinicCommand extends ClinicCommand {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(NewModel model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
