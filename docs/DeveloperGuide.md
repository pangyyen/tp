# Developer Guide for CareCentral

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component     
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the clinic book data i.e., all `Patient` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Patient` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)



### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-F08-1/tp/blob/master/src/main/java/seedu/cc/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.cc.commons` package.]

## Implementation

### Medical History
CareCentral also allows users to store and access patient medical history, including past diagnoses, allergies,
and medications. This enables healthcare professionals to provide more accurate and efficient care during patient
visits, ensuring continuity of care. User can add a `MedicalHistoryEvent` with the `Date` with format YYYY-MM-DD,
the `MedicalCondition` and the `Treatment` received by the patient.

The `MedicalHistoryEvent` will then be shown as a list of `MedicalHistoryEvent` in the `Patient`'s `Medical History`.
The MedicalHistory will be facilitated using the FilteredList, although the current implementation does not allow
filtering.

When the user starts an application, there will be an empty `CurrentMedicalHistoryEventList`. It will be populated when the
user executes `list-medical-history` command.

The `MedicalHistory` for each `Patient` will be stored in clinicbook.json as a nested attribute.

### Appointment-related features

#### Proposed Implementation

The appointment-related features are facilitated by the `appointment` package. The `appointment` package includes `AppointmentEvent`, `PatientAppointmentList` and `ClinicBookAppointmentList`.
The `PatientAppointmentList` a an attribute for each `Patient` and stores a list of `AppointmentEvent`. The `ClinicBookAppointmentList` is a list of `AppointmentEvent` that is used for display purposes.

The sequence diagram below shows the interaction between the `Logic` component and the `Model` component when the user issues the `add-appointment` command.



### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by the `VersionedClinicBook` class. The `VersionedClinicBook` class extends `ClinicBook` with an undo/redo history, stored internally as `clinicBookStateList` and `currentStatePointer`. Additionally, it implementes the following operations:

* `VersionedClinicBook#commit()` — Saves the current address book state in its history.
* `VersionedClinicBook#undo()` — Restores the previous address book state from its history.
* `VersionedClinicBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

### \[Proposed\] Pharmacy Integration

#### Proposed Implementation

The proposed pharmacy integration is facilitated by the `Pharmacy` class. The `Pharmacy` class extends `ClinicBook` with a list of `Medicine` and `Prescription` that is stored internally as `medicineList` and `prescriptionList`. Additionally, it implementes the following operations:

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


## User Stories

### Doctor

- As a doctor (Age: 30-60), I can easily create, access, and edit patient records on my smartphone app to provide accurate and efficient care during patient visits.
- As a doctor, I can share and edit surgical notes and post-operative care instructions with my patients through the app, ensuring they have all the necessary information.
- As a doctor, I can securely store patient therapy notes in the app, ensuring confidentiality and continuity of care.
- As a doctor (Age: 30-60), I want to schedule, reschedule, and cancel appointments on the app to manage my day-to-day practice schedule efficiently and reduce no-shows.
- As a doctor (Age: 30-60), I can send a patient referral to a specialist using the app so that the referred professional has immediate access to necessary patient data, ensuring smooth continuation of care.
- As a doctor (Age: 30-60), I want to prescribe medications directly through the app, so that pharmacies can promptly prepare medicines and patients receive timely notifications.
- As a doctor (Age: 30-60), I can view patient feedback and reviews about their visit, enabling me to continuously improve my service and better meet their needs.

## Use Cases

### Nice to Have

1. **Delete Confirmation**
   - **MSS:**
      1. Doctor selects a patient record to delete.
      2. App prompts for confirmation.
      3. Doctor confirms the deletion.
      4. App deletes the selected record.

2. **View All Appointments**
   - **MSS:**
      1. Doctor chooses to view all appointments.
      2. App displays the list of all appointments.

3. **Edit Patient Record/Appointment**
   - **MSS:**
      1. Doctor selects a patient record or appointmentEvent to edit.
      2. App prompts for the new information.
      3. Doctor enters the updated information.
      4. App saves the changes.



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

1. User issues the command to schedule a new appointment for a patient using the patient's index and provides the appointment details.
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

1. User issues the command to add a medical history for a patient using the patient's index and provides the medical history details.
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

1. User requests to edit a specific medical history record using the medical history's index and the patient's index, and provides the new details.
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

1. **Performance Requirements:** The app should be able to handle up to 500 patient records without significant performance degradation.
2. **Security Requirements:** All patient data must be stored securely to ensure confidentiality and privacy.
3. **Usability Requirements:** The app should be user-friendly, with a clean and intuitive CLI interface that can be easily navigated by healthcare professionals.
4. **Compatibility Requirements:** The app should be compatible with both iOS and Android devices, ensuring accessibility for a wide range of users.

### Glossary

- **App:** Refers to the CareCentral application.
- **Healthcare Professional:** A user who is a certified medical practitioner, including doctors, nurses,
- and other medical staff.
- **Patient Record:** A digital file within the app containing all relevant information about a patient, including
- medical history, prescriptions, and appointmentEvent records.
- **User:** Refers to the healthcare professionals using the CareCentral app.
