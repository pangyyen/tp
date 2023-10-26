package seedu.cc.logic.parser.appointment;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.appointmentcommands.ListAppointmentEventsCommand;
import seedu.cc.logic.parser.Parser;
import seedu.cc.logic.parser.ParserUtil;
import seedu.cc.logic.parser.exceptions.ParseException;


public class ListAppointmentEventsCommandParser implements Parser<ListAppointmentEventsCommand> {

    public ListAppointmentEventsCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListAppointmentEventsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAppointmentEventsCommand.MESSAGE_USAGE), pe);
        }
    }
}