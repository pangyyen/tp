package seedu.cc.logic.commands;

import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;

public class SwitchCommand  extends Command {

    public static final String COMMAND_WORD = "switch";
    public static final String MESSAGE_SUCCESS = "Switched to %d tab!";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
