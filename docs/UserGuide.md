# User Guide

<img src="images/Ui.png" >

# Introduction

CareCentral is an app designed specifically for healthcare professionals, including doctors, nurses, and hospital
staff aged between 25-60 years. It is a desktop app optimised for fast-typers and is designed to help healthcare
professionals manage their patients' medical journey.

# Table of Contents

* Table of Contents
  {:toc}

<!--- TODO: add release link --->

# 1. Quick Start

1. Ensure you have Java 11 installed in your Computer.
2. Download the latest `carecentral.jar` from [here]()
3. Copy the file to the folder you want to use as the home folder for CareCentral.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.  
   ![Ui](images/Ui.png)
5. For Mac users encountering this issue, follow
   this [guide](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Run-JAR-file-example-windows-linux-ubuntu).
   <img width="275" height="280" src="images/mac_issue.png">
6. Type the command in the command box and press Enter to execute it.  
   e.g. typing `help` and pressing Enter will open the help window.
   Some example commands you can try:
    * `add-patient n/John Doe ic/S1234567A a/45 p/91234567`
    * `list-patients`
    * `delete-patient 1`
    * `exit`

# 2. Features

Notes about the command format:

- Words in UPPER_CASE are the parameters to be supplied by the user.
  e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.

- Items in square brackets are optional.
  e.g n/NAME [tag/TAG] can be used as n/John Doe tag/friend or as n/John Doe.

- Items with …​ after them can be used multiple times including zero times.
  e.g. [tag/TAG]…​ can be used as   (i.e. 0 times), tag/friend, tag/friend tag/family etc.

- Parameters can be in any order.
  e.g. if the command specifies n/NAME p/PHONE_NUMBER, p/PHONE_NUMBER n/NAME is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.
  e.g. if you specify p/12341234 p/56785678, only p/56785678 will be taken.

- Extraneous parameters for commands that do not take in parameters (such as help, list, exit and clear) will be
  ignored.
  e.g. if the command specifies help 123, it will be interpreted as help.

## 2.1. Patients Related Features

### 2.1.1. Create Patient Record

**What it does:**  
Adds a new patient record to the system.

**Command Format:**  
`add-patient n/NAME ic/NRIC a/AGE p/PHONE_NUMBER e/email [t/TAG]…`

**Example Commands:**  
`add-patient n/John Doe ic/S0123456A a/45 p/12341234 e/johndoe@example.com`

**Parameters:**

| Parameters       | Explanation                 | Constraints                                                                                                                                                 |            
|------------------|-----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `n/NAME`         | full name of the patient    | must only contain alphanumeric characters and spaces, and it should not be blank                                                                            |
| `p/PHONE_NUMBER` | phone number of the patient | must be **entirely numeric** and exactly 8 digits long                                                                                                      |
| `ic/NRIC`        | NRIC of the patient         | must be **entirely alphanumeric** and follow Singapore NRIC format. More details [here](https://en.wikipedia.org/wiki/National_Registration_Identity_Card). |
| `a/AGE`          | age of the patient          | must be a positive integer                                                                                                                                  |
| `e/email`        | email of the patient        | must be a valid email address                                                                                                                               |
| `[t/TAG]…`       | tags of the patient         | must only contain alphanumeric characters and spaces, and it should not be blank                                                                            |

<!--- TODO: verify all of these --->
**Expected Output (Success):**  
CLI: A new entry appears in the list of patients.  
Message: "Successfully added patient: [Patient Details]"

**Expected Output (Failure):**

- Message: "Invalid input. Please enter a valid name."
- Message: "Invalid input. Please enter a valid NRIC (8-16 alphanumeric characters)."
- Message: "Invalid input. Age should be between 0 to 120"
- Message: "Invalid input. Please enter a valid phone number."

---

## 2.1.2. List Patients

**What it does:**  
Finds persons whose names contain any of the given keywords.

**Command Format:**  
`list-patients`

---

## 2.1.3. Edit Patient Record

**What it does:**
Edits a patient record at the specified `INDEX` in the system.

**Command Format:**
`edit-patient INDEX [n/NAME] [ic/NRIC] [a/AGE] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]…`

**Example Commands:**
`edit-patient 5 n/John Doe ic/S0123456A a/45 p/12341234 e/johndoe@example.com t/critical`

**Parameters:**

| Parameters       | Explanation                                        | Constraints                                                                                                                                                 |            
|------------------|----------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`          | index of the patient in the displayed patient list | must be a positive integer                                                                                                                                  |
| `n/NAME`         | full name of the patient                           | must only contain alphanumeric characters and spaces, and it should not be blank                                                                            |
| `p/PHONE_NUMBER` | phone number of the patient                        | must be **entirely numeric** and exactly 8 digits long                                                                                                      |
| `ic/NRIC`        | NRIC of the patient                                | must be **entirely alphanumeric** and follow Singapore NRIC format. More details [here](https://en.wikipedia.org/wiki/National_Registration_Identity_Card). |
| `a/AGE`          | age of the patient                                 | must be a positive integer                                                                                                                                  |
| `e/email`        | email of the patient                               | must be a valid email address                                                                                                                               |
| `[t/TAG]…`       | tags of the patient                                | must only contain alphanumeric characters and spaces, and it should not be blank                                                                            |

<!--- TODO: verify all of these --->
**Expected Output (Success):**

- Message: "Successfully edited patient: [Patient Details]"

**Expected Output (Failure):**

- Message: "Invalid input. Please enter a valid index or details."

---

## 2.1.4. Delete Patient Record

**What it does:**  
Removes a patient record from the system.

**Command Format:**  
`delete-patient INDEX`

**Example Commands:**  
`delete-patient 2`

**Acceptable Values:**

- **INDEX:** Positive integer

<!--- TODO: verify all of these --->
**Expected Output (Success):**

- Message: "Successfully deleted patient."

**Expected Output (Failure):**

- Message: "Invalid index. Please enter a valid index."

## 5. Add Appointment

**What it does:**  
Schedules a new appointmentEvent for a patient.

**Command Format:**  
`add-appt INDEX d/DATE t/TIME`

**Example Commands:**  
`add-appt 1 d/2023-10-01 tm/14:00`

**Acceptable Values:**

- **INDEX:** Positive integer
- **DATE:** YYYY-MM-DD
- **TIME:** HH:MM (24-hour format)

**Expected Output (Success):**  
CLI: A new entry appears in the list of appointments.  
Message: "Successfully added appointmentEvent: [Appointment Details]"

**Expected Output (Failure):**  
Message: "Invalid input. Please enter a valid patient index, date, or time."

## 6. List All Appointment

**What it does:**  
Displays all appointments sorted by date.

**Command Format:**  
`list-appointments INDEX`

**Example Commands:**  
`list-appointments 1`

**Expected Output (Success):**  
CLI: The list of appointments is displayed.  
Message: "Displaying X number of appointments."

**Expected Output (Failure):**  
Message: "No appointments found."

## 7. Edit Appointment

**What it does:**  
Edits existing appointmentEvent details.

**Command Format:**  
`edit-appointmentEvent INDEX [pi/patient-index] [d/DATE] [t/TIME]`

**Example Commands:**  
`edit-appointmentEvent 2 pi/7 d/2023-10-05 t/16:00`

**Acceptable Values:**

- **INDEX:** Positive integer
- **DATE:** YYYY-MM-DD
- **TIME:** HH:MM (24-hour format)

**Expected Output (Success):**  
CLI: The edited appointmentEvent entry is updated in the list.  
Message: "Successfully edited appointmentEvent: [Appointment Details]"

**Expected Output (Failure):**  
Message: "Invalid input. Please enter a valid index or details."

## 8. Delete Appointment

**What it does:**  
Removes an appointmentEvent from the system.

**Command Format:**  
`delete-appointmentEvent INDEX`

**Example Commands:**  
`delete-appointmentEvent 3`

**Acceptable Values:**

- **INDEX:** Positive integer

**Expected Output (Success):**  
CLI: The deleted appointmentEvent entry is removed from the list.  
Message: "Successfully deleted appointmentEvent."

**Expected Output (Failure):**  
Message: "Invalid index. Please enter a valid appointmentEvent index."

---

## 9. Add Medical History

**What it does:**
Adds a medical history to a patient record.

**Command Format:**
`add-medical-history INDEX [d/DATE] [mc/MEDICAL_CONDITION] [t/TREATMENT]`

**Acceptable Values:**

- **INDEX:** Positive integer
- **DATE:** YYYY-MM-DD
- **MEDICAL_CONDITION:** Alphabetic characters and spaces only
- **TREATMENT:** Alphabetic characters and spaces only

**Expected Output (Success):**
Message: "Successfully added medical history for patient: [Medical History Details]"

**Expected Output (Failure):**

- Message: "Invalid input. Please enter a valid index or details."

---

## 10. List Medical History

**What it does:**
Lists the medical history of a patient.

**Command Format:**
`list-medical-history INDEX`

**Acceptable Values:**

- **INDEX:** Positive integer

**Expected Output (Success):**
Message: "Successfully listed medical history for patient: [Medical History Details]"

**Expected Output (Failure):**

- Message: "Invalid input. Please enter a valid index or details."

---

## 11. Edit Medical History

**What it does:**
Edits a medical history of a patient.

**Command Format:**
`edit-medical-history INDEX [pi/PATIENT_INDEX] [d/DATE] [mc/MEDICAL_CONDITION] [t/TREATMENT]`

**Acceptable Values:**

- **INDEX:** Positive integer
- **PATIENT_INDEX:** Positive integer
- **DATE:** YYYY-MM-DD
- **MEDICAL_CONDITION:** Alphabetic characters and spaces only
- **TREATMENT:** Alphabetic characters and spaces only

**Expected Output (Success):**
Message: "Successfully edited medical history for patient: [Medical History Details]"

**Expected Output (Failure):**

- Message: "Invalid input. Please enter a valid index or details."

---

## 12. Delete Medical History

**What it does:**
Deletes a medical history of a patient.

**Command Format:**
`delete-medical-history INDEX [pi/PATIENT_INDEX]`

**Acceptable Values:**

- **INDEX:** Positive integer
- **PATIENT_INDEX:** Positive integer

**Expected Output (Success):**
Message: "Successfully deleted medical history for patient: [Medical History Details]"

**Expected Output (Failure):**

- Message: "Invalid input. Please enter a valid index or details."

---
