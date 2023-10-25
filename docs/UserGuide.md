# User Guide

<img src="images/Ui.png" >

## 1. Create Patient Record

**What it does:**  
Adds a new patient record to the system.

**Command Format:**  
`add-patient n/NAME ic/NRIC a/AGE p/PHONE_NUMBER`


**Example Commands:**  
`add-patient n/Jane Doe ic/0123456A a/45 e/janedoe@example.com`

**Acceptable Values:**
- **NAME:** Alphabetic characters and spaces only
- **NRIC:** 8-16 Alphanumeric characters
- **AGE:** Integer values between 0 and 120
- **PHONE_NUMBER:** 8-12 digits integer

**Expected Output (Success):**  
CLI: A new entry appears in the list of patients.  
Message: "Successfully added patient: [Patient Details]"

**Expected Output (Failure):**
- Message: "Invalid input. Please enter a valid name."
- Message: "Invalid input. Please enter a valid NRIC (8-16 alphanumeric characters)."
- Message: "Invalid input. Age should be between 0 to 120"
- Message: "Invalid input. Please enter a valid phone number."
- 
---
## 2. List Patients

**What it does:**  
Finds persons whose names contain any of the given keywords.

**Command Format:**  
`list-patients`

---
## 3. Edit Patient Record

**What it does:**
Edits a patient record in the system.

**Command Format:**
`edit-patient INDEX [n/NAME] [ic/NRIC] [a/AGE] [p/PHONE_NUMBER]`

**Acceptable Values:**
- **INDEX:** Positive integer
- **NAME:** Alphabetic characters and spaces only
- **NRIC:** 8-16 Alphanumeric characters
- **AGE:** Integer values between 0 and 120
- **PHONE_NUMBER:** 8-12 digits integer

**Expected Output (Success):**
Message: "Successfully edited patient: [Patient Details]"

**Expected Output (Failure):**
- Message: "Invalid input. Please enter a valid index or details."

---

## 4. Delete Patient Record

**What it does:**  
Removes a patient record from the system.

**Command Format:**  
`delete-patient INDEX`


**Example Commands:**  
`delete-patient 2`


**Acceptable Values:**
- **INDEX:** Positive integer

**Expected Output (Success):**  
Message: "Successfully deleted patient."

**Expected Output (Failure):**  
Message: "Invalid index. Please enter a valid index."

## 5. Add Appointment

**What it does:**  
Schedules a new appointment for a patient.

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
Message: "Successfully added appointment: [Appointment Details]"

**Expected Output (Failure):**  
Message: "Invalid input. Please enter a valid patient index, date, or time."

## 6. List All Appointment

**What it does:**  
Displays all appointments sorted by date.

**Command Format:**  
`list-appointments`

**Example Commands:**  
`list-appointments`

**Expected Output (Success):**  
CLI: The list of appointments is displayed.  
Message: "Displaying X number of appointments."

**Expected Output (Failure):**  
Message: "No appointments found."

## 7. Edit Appointment

**What it does:**  
Edits existing appointment details.

**Command Format:**  
`edit-appointment INDEX [d/DATE] [t/TIME]`

**Example Commands:**  
`edit-appointment 2 d/2023-10-05 t/16:00`

**Acceptable Values:**
- **INDEX:** Positive integer
- **DATE:** YYYY-MM-DD
- **TIME:** HH:MM (24-hour format)

**Expected Output (Success):**  
CLI: The edited appointment entry is updated in the list.  
Message: "Successfully edited appointment: [Appointment Details]"

**Expected Output (Failure):**  
Message: "Invalid input. Please enter a valid index or details."

## 8. Delete Appointment

**What it does:**  
Removes an appointment from the system.

**Command Format:**  
`delete-appointment INDEX`

**Example Commands:**  
`delete-appointment 3`

**Acceptable Values:**
- **INDEX:** Positive integer

**Expected Output (Success):**  
CLI: The deleted appointment entry is removed from the list.  
Message: "Successfully deleted appointment."

**Expected Output (Failure):**  
Message: "Invalid index. Please enter a valid appointment index."

---

