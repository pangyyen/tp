package seedu.cc.model.medicalhistory;

import java.time.LocalDateTime;

/**
 * Represents a medical history event's date.
 */
public class Date {

    private LocalDateTime date;

    public Date(String date) {
        this.date = LocalDateTime.parse(date);
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
