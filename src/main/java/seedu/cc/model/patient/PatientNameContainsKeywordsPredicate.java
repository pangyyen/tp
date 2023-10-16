package seedu.cc.model.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.cc.commons.util.StringUtil;
import seedu.cc.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class PatientNameContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public PatientNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient Patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(Patient.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientNameContainsKeywordsPredicate)) {
            return false;
        }

        PatientNameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (PatientNameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
