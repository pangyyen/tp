package seedu.cc.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_PATIENT_INDEX;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_TREATMENT;

import java.time.LocalDateTime;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.EditCommand;
import seedu.cc.logic.commands.medhisteventcommands.EditMedicalHistoryEventCommand;
import seedu.cc.logic.parser.exceptions.ParseException;

public class EditMedicalHistoryEventCommandParser implements Parser<EditMedicalHistoryEventCommand>{
    public EditMedicalHistoryEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_INDEX, PREFIX_MEDICAL_CONDITION, PREFIX_TREATMENT
                        , PREFIX_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_INDEX, PREFIX_MEDICAL_CONDITION, PREFIX_TREATMENT
                , PREFIX_DATE);

        Index patientIndex;

        if (!argMultimap.getValue(PREFIX_PATIENT_INDEX).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE), pe);
        }

        EditMedicalHistoryEventCommand.EditMedicalHistoryEventDescriptor editMedicalHistoryEventDescriptor =
                new EditMedicalHistoryEventCommand.EditMedicalHistoryEventDescriptor();


        if (argMultimap.getValue(PREFIX_MEDICAL_CONDITION).isPresent()) {
            editMedicalHistoryEventDescriptor.setMedicalCondition(ParserUtil.parseMedicalCondition(argMultimap
                    .getValue(PREFIX_MEDICAL_CONDITION).get()));
        }

        if (argMultimap.getValue(PREFIX_TREATMENT).isPresent()) {
            editMedicalHistoryEventDescriptor.setTreatment(ParserUtil.parseTreatment(argMultimap
                    .getValue(PREFIX_TREATMENT).get()));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editMedicalHistoryEventDescriptor.setDate(LocalDateTime.parse(argMultimap
                    .getValue(PREFIX_DATE).get()));
        }

        return new EditMedicalHistoryEventCommand(index, patientIndex, editMedicalHistoryEventDescriptor);
    }

}
