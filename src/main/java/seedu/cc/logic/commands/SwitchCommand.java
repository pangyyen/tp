package seedu.cc.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;

public class SwitchCommand  extends Command {

    public static final String COMMAND_WORD = "switch";
    public static final String MESSAGE_SUCCESS = "Switched to";

    private final Index tabIndex;

    public SwitchCommand(Index tabIndex) {
        requireNonNull(tabIndex);
        this.tabIndex = tabIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(MESSAGE_SUCCESS, tabIndex.getOneBased() + " tab"));
    }
}
