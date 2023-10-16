package seedu.cc.logic.parser;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.DeletePatientCommand;
import seedu.cc.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeletePatientCommandParser implements ParserClinic<DeletePatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePatientCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePatientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientCommand.MESSAGE_USAGE), pe);
        }
    }

}
