package seedu.cc.logic.parser;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.DeleteCommand;
import seedu.cc.logic.commands.medhisteventcommands.ListMedicalHistoryEventCommand;
import seedu.cc.logic.parser.exceptions.ParseException;

public class ListMedicalHistoryEventsCommandParser implements Parser<ListMedicalHistoryEventCommand>{

    public ListMedicalHistoryEventCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListMedicalHistoryEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
