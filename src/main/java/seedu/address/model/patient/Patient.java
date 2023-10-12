package seedu.address.model.patient;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Set;

/* Represents a Patient (WIP) */
public class Patient extends Person {
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Patient(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }

    //getters and setters
    public Name getName() {
        return super.getName();
    }

    public Phone getPhone() {
        return super.getPhone();
    }

    public Email getEmail() {
        return super.getEmail();
    }

    public Address getAddress() {
        return super.getAddress();
    }

    public Set<Tag> getTags() {
        return super.getTags();
    }

    public boolean isSamePerson(Patient otherPatient) {
        return super.isSamePerson(otherPatient);
    }

    public boolean equals(Object other) {
        return super.equals(other);
    }

    public String toString() {
        return super.toString();
    }
}
