package seedu.cc.model.appointment;

import java.util.ArrayList;

public class PatientAppointmentList {

    private final ArrayList<AppointmentEvent> appointmentEventList;

    /**
     * Creates an empty AppointmentEvent List.
     */
    public PatientAppointmentList() {
        this.appointmentEventList = new ArrayList<>();
    }


    /**
     * Creates an Appointments using the Appointments in the {@code toBeCopied}
     */
    public void addAppointment(AppointmentEvent appt) {
        appointmentEventList.add(appt);
    }

    /**
     * Returns the ArrayList of Appointments.
     */
    public ArrayList<AppointmentEvent> getAppointmentList() {
        return this.appointmentEventList;
    }

    /**
     * Sets the AppointmentEvent at the specified index to the edited AppointmentEvent.
     */
    public void setAppointment(AppointmentEvent apptToEdit, AppointmentEvent editedAppt) {
        int index = appointmentEventList.indexOf(apptToEdit);
        if (index >= 0 && index < appointmentEventList.size()) {
            appointmentEventList.set(index, editedAppt);
        }
    }

    /**
     * Deletes the specified AppointmentEvent from the Appointments.
     */
    public void deleteAppointment(AppointmentEvent eventToDelete) {
        int index = appointmentEventList.indexOf(eventToDelete);
        if (index >= 0 && index < appointmentEventList.size()) {
            appointmentEventList.remove(index);
        }
    }

    /**
     * Returns true if the Appointments contains the specified AppointmentEvent.
     */
    public boolean hasAppointment(AppointmentEvent event) {
        int s = 1;
        boolean as = appointmentEventList.get(0).equals(event);
        return appointmentEventList.contains(event);
    }
}
