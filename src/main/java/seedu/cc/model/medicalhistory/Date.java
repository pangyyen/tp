package seedu.cc.model.medicalhistory;

import java.time.LocalDate;

/**
 * Represents a medical history event's date.
 */
public class Date {

    private LocalDate date;

    public Date(String date) {
        this.date = LocalDate.parse(date);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return date.equals(otherDate.date);
    }
}
