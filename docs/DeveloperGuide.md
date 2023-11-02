---
layout: page
title: Developer Guide
---
### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of
classes [`Main`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/Main.java)
and [`MainApp`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/MainApp.java)) is in
charge of the app launch and shut down.

* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete-p 1`.
=======

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API
** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API
call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates
   a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which
   is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API
** : [`Model.java`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the clinic book data i.e., all `Patient` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Patient` objects (e.g., results of a search query) as a separate _filtered_ list
  which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be
  bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

### Storage component

**API
** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

* can save both address book data and user preference data in JSON format, and read them back into corresponding
  objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.cc.commons` package.]

## Implementation

### Medical History

User can add a `MedicalHistoryEvent` with the `Date` with format YYYY-MM-DD,
the `MedicalCondition` and the `Treatment` received by the patient.

The `MedicalHistoryEvent` will then be shown as a list of `MedicalHistoryEvent` in the `Patient`'
s `PatientMedicalHistory`.
The MedicalHistory will be facilitated using the FilteredList, although the current implementation does not allow
filtering.

When the user starts an application, there will be an empty `ClinicBookMedicalHistory`. It will be populated when the
user executes `list-medical-history` command.

The `PatientMedicalHistory` for each `Patient` will be stored in clinicbook.json as a nested attribute.

The difference between `PatientMedicalHistory` class and `ClinicBookMedicalHistory` class is
that `PatientMedicalHistory` is a list of
`MedicalHistoryEvent` while `ClinicBookMedicalHistory` is a list of `MedicalHistoryEvent` that is currently being
displayed.

#### An example usage scenario and how the medical history mechanism behaves at each step is shown below.

Step 1. The user launches the application for the first time. `ClinicBookMedicalHistory` contains no default list
of `MedicalHistoryEvent`.

Step 2. The user inputs `list-md` to list all diaries. `Ui` passes the input to `Logic`. `Logic` then uses a
few `Parser` classes to extract layers of information out as seen from steps 3 to 5.

Step 3. `Logic` passes the user input to `ClinicBookParser`. `ClinicBookParser` identifies that this is
a `ListMedicalHistoryEventCommand` through the word "list-md". It then creates a `ListMedicalHistoryEventCommandParser`
to parse it into a `ListMedicalHistoryEventCommand` and return.

Step 4. Logic finally gets the `ListMedicalHistoryEventCommand` and execute it. The execution firstly
calls `Model#getFilteredPatientList()` to get the patients that are currently being displayed. It then gets
the `Patient` from the list using the index provided by the user.
It then calls `Model#listMedicalHistoryEvents(Patient patient)` to set `ClinicBookMedicalHistory` as the list
of `MedicalHistoryEvent` that belongs to the patient.
This execution then returns a `CommandResult` to `Ui`, containing the response to the user.

Step 5. UI displays the response in the CommandResult. In addition, UI will change to display the list
of `MedicalHistoryEvent` after model updates `filteredMedicalHistoryEvents`, since `Ui` is constantly listening for the
change in `Model`.

The Sequence Diagram below shows how the components interact with each other for the above mentioned scenario

<img src="images/ListMedicalHistoryEventSequenceDiagram.png" width="550"/>

### Appointment-related features

#### Proposed Implementation

The appointment-related features are facilitated by the `appointment` package. The `appointment` package
includes `AppointmentEvent`, `PatientAppointmentList` and `ClinicBookAppointmentList`.
The `PatientAppointmentList` a an attribute for each `Patient` and stores a list of `AppointmentEvent`.
The `ClinicBookAppointmentList` is a list of `AppointmentEvent` that is used for display purposes.

The sequence diagram below shows the interaction between the `Logic` component and the `Model` component when the user
issues the `add-appointment` command.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by the `VersionedClinicBook` class. The `VersionedClinicBook` class
extends `ClinicBook` with an undo/redo history, stored internally as `clinicBookStateList` and `currentStatePointer`.
Additionally, it implementes the following operations:

* `VersionedClinicBook#commit()` — Saves the current address book state in its history.
* `VersionedClinicBook#undo()` — Restores the previous address book state from its history.
* `VersionedClinicBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

### \[Proposed\] Pharmacy Integration

#### Proposed Implementation

The proposed pharmacy integration is facilitated by the `Pharmacy` class. The `Pharmacy` class extends `ClinicBook` with
a list of `Medicine` and `Prescription` that is stored internally as `medicineList` and `prescriptionList`.
Additionally, it implementes the following operations:

* `Pharmacy#addMedicine()` — Adds a `Medicine` to the `medicineList`.
* `Pharmacy#deleteMedicine()` — Deletes a `Medicine` from the `medicineList`.
* `Pharmacy#addPrescription()` — Adds a `Prescription` to the `prescriptionList`.
* `Pharmacy#deletePrescription()` — Deletes a `Prescription` from the `prescriptionList`.
* `Pharmacy#findMedicine()` — Finds a `Medicine` from the `medicineList`.
* `Pharmacy#listMedicine()` — Lists all `Medicine` from the `medicineList`.
* `Pharmacy#listPrescription()` — Lists all `Prescription` from the `prescriptionList`.
* `Pharmacy#updateMedicine()` — Updates a `Medicine` from the `medicineList`.
* `Pharmacy#updatePrescription()` — Updates a `Prescription` from the `prescriptionList`.

Given below is the UML diagram for the `Pharmacy` class.

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

## **Appendix: Requirements**

### Product scope

## User Stories

**Target user profile**:

- medical administrators who need to oversee the operations of a clinic who has a substantial number of patients
- favor desktop applications over other platforms
- prefer a CLI-based interface over a GUI-based interface
- are comfortable typing commands

**Value proposition**: The app helps the user to manage patient records, medical histories and appointments.

## Use Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …                | I can …                             | To fulfill …                                        |
|----------|-----------------------|-------------------------------------|-----------------------------------------------------|
| `* * *`  | medical administrator | add a patient record                | Efficiently maintain patient records                |
| `* * *`  | medical administrator | list all patients                   | Easily view the complete patient list               |
| `* * *`  | medical administrator | edit a patient record               | Keep patients' information up to date               |
| `* * *`  | medical administrator | delete a patient record             | Remove patients who are no longer under care        |
| `* *`    | medical administrator | give patients different priorities  | Prioritize patient care effectively                 |
| `* `     | medical administrator | list patients by priority           | Quickly access patients based on priority           |
| `* * *`  | medical administrator | add an appointment                  | Schedule appointments for patients                  |
| `* * *`  | medical administrator | list all appointments of a patient  | Access a patient's complete appointment history     |
| `* * *`  | medical administrator | edit an appointment                 | Modify appointment details as needed                |
| `* * *`  | medical administrator | delete an appointment               | Eliminate unnecessary appointments                  |
| `*`      | medical administrator | cancel an appointment               | Efficiently manage appointment cancellations        |
| `* *`    | medical administrator | sort appointments by date           | Easily view appointments in chronological order     |
| `* `     | medical administrator | mark an appointment as missed       | Record the status of missed appointments            |
| `*`      | medical administrator | mark an appointment as attended     | Record the status of attended appointments          |
| `* * `   | medical administrator | add prescriptions to an appointment | Easily document prescribed medications for patients |
| `* * *`  | medical administrator | add a medical history               | Keep comprehensive medical histories for patients   |
| `* * *`  | medical administrator | list a patient's medical history    | Access and review patient medical histories         |
| `* * *`  | medical administrator | edit a medical history              | Update patient medical histories as necessary       |
| `* * *`  | medical administrator | delete a medical history            | Remove outdated or irrelevant medical histories     |
| `* * *`  | medical administrator | find a patient                      | Quickly locate specific patients                    |
| `* *`    | medical administrator | find an appointment                 | Easily search for specific appointments             |
| `* * `   | medical administrator | find a medical history              | Quickly retrieve specific patient medical histories |
| `*`      | medical administrator | undo previous action                | Correct any inadvertent actions                     |
| `*`      | medical administrator | redo previous action                | Reapply actions that were previously undone         |
| `* * *`  | medical administrator | exit the app                        | Close the application with ease                     |
| `* * *`  | medical administrator | get help                            | Access a list of available commands and assistance  |

### Use Cases

#### Use case: UG01 - Create Patient Record

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User issues the command to add a new patient record with the required details.
2. System adds the new patient record.
3. System displays a success message along with the details of the added patient.

   Use case ends.

**Extensions**

* 2a. The input details are invalid.
    * 2a1. System shows an error message.

      Use case ends.

#### Use case: UG02 - List Patients

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User requests to list all patients.
2. System retrieves and shows a list of patients.

   Use case ends.

**Extensions**

* 2a. The list is empty.
    * 2a1. System informs the user that the list is empty.

      Use case ends.

#### Use case: UG03 - Edit Patient Record

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User requests to edit a specific patient record using the patient's index in the list and provides the new details.
2. System validates the input details.
3. System updates the patient record with the new details.
4. System displays a success message along with the updated details of the patient.

   Use case ends.

**Extensions**

* 2a. The given index or details is invalid.
    * 2a1. System shows an error message with the correct input format.

      Use case ends.

#### Use case: UG04 - Delete Patient Record

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User requests to delete a specific patient record using the patient's index in the list.
2. System deletes the patient record.
3. System displays a success message.

   Use case ends.

**Extensions**

* 1a. The given index is invalid.
    * 1a1. System shows an error message.

      Use case ends.

#### Use case: UG05 - Add Appointment

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User issues the command to schedule a new appointment for a patient using the patient's index and provides the
   appointment details.
2. System validates the input details.
3. System adds the new appointment.
4. System displays a success message along with the details of the added appointment.

   Use case ends.

**Extensions**

* 2a. The input details are invalid.
    * 2a1. System shows an error message.

      Use case ends.

#### Use case: UG06 - List All Appointments

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User requests to list all appointments.
2. System retrieves and shows a list of appointments.

   Use case ends.

**Extensions**

* 2a. The list is empty.
    * 2a1. System informs the user that there are no appointments.

      Use case ends.

#### Use case: UG07 - Edit Appointment

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User requests to edit a specific appointment using the appointment's index in the list and provides the new details.
2. System validates the input details.
3. System updates the appointment with the new details.
4. System displays a success message along with the updated details of the appointment.

   Use case ends.

**Extensions**

* 2a. The given index is invalid.
    * 2a1. System shows an error message.

      Use case ends.

* 2b. The input details are invalid.
    * 2b1. System shows an error message.

      Use case ends.

#### Use case: UG08 - Delete Appointment

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User requests to delete a specific appointment using the appointment's index in the list.
2. System deletes the appointment.
3. System displays a success message.

   Use case ends.

**Extensions**

* 1a. The given index is invalid.
    * 1a1. System shows an error message.

      Use case ends.

#### Use case: UG09 - Add Medical History

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User issues the command to add a medical history for a patient using the patient's index and provides the medical
   history details.
2. System validates the input details.
3. System adds the medical history to the patient's record.
4. System displays a success message along with the details of the added medical history.

   Use case ends.

**Extensions**

* 2a. The input details are invalid.
    * 2a1. System shows an error message.

      Use case ends.

#### Use case: UG10 - List Medical History

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User requests to list the medical history of a specific patient using the patient's index.
2. System retrieves and shows the medical history of the patient.

   Use case ends.

**Extensions**

* 2a. The given index is invalid.
    * 2a1. System shows an error message.

      Use case ends.

* 2b. There is no medical history for the selected patient.
    * 2b1. System informs the user that there is no medical history for the selected patient.

      Use case ends.

#### Use case: UG11 - Edit Medical History

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User requests to edit a specific medical history record using the medical history's index and the patient's index,
   and provides the new details.
2. System validates the input details.
3. System updates the medical history record with the new details.
4. System displays a success message along with the updated details of the medical history.

   Use case ends.

**Extensions**

* 2a. The given indexes are invalid.
    * 2a1. System shows an error message.

      Use case ends.

* 2b. The input details are invalid.
    * 2b1. System shows an error message.

      Use case ends.

#### Use case: UG12 - Delete Medical History

**Actor:** Doctor, Receptionist, Nurse

**MSS**

1. User requests to delete a specific medical history record using the medical history's index and the patient's index.
2. System deletes the medical history record.
3. System displays a success message.

   Use case ends.

**Extensions**

* 1a. The given indexes are invalid.
    * 1a1. System shows an error message.

      Use case ends.

### Non-Functional Requirements

1. **Performance Requirements:** The app should be able to handle up to 500 patient records without significant
   performance degradation.
2. **Security Requirements:** All patient data must be stored securely to ensure confidentiality and privacy.
3. **Usability Requirements:** The app should be user-friendly, with a clean and intuitive CLI interface that can be
   easily navigated by healthcare professionals.
4. **Compatibility Requirements:** The app should be compatible with both iOS and Android devices, ensuring
   accessibility for a wide range of users.

### Glossary

- **App:** Refers to the CareCentral application.
- **Healthcare Professional:** A user who is a certified medical practitioner, including doctors, nurses,
- and other medical staff.
- **Patient Record:** A digital file within the app containing all relevant information about a patient, including
- medical history, prescriptions, and appointmentEvent records.
- **User:** Refers to the healthcare professionals using the CareCentral app.

---

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a patient

1. Deleting a patient while all patients are being shown

    1. Prerequisites: List all persons using the `list-patients` command. Multiple persons in the list.

    1. Test case: `delete-patient 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `delete-patient 1`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_